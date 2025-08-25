import {JSDOM} from "jsdom";
import path from "path";
import {marked} from "marked";
import * as fs from 'node:fs';

const CONTENT_DIR = 'code-smells';
const STATIC_DIR = 'public';

let BOILERPLATE_HTML = 
	`
		<!DOCTYPE html>
		<html lang='en'>
			<head>
				<meta charset="UTF-8"/>
				<meta name="viewport" content="width=device-width,initial-scale=1" />
				<link rel="stylesheet" href="/styles.css"/>
			</head>
			<body>
				<nav>
				</nav>
				<main>
				</main>
			</body>
		</html
	`

function addNavToIndex(nav) {
	const staticDir = path.join(process.cwd(), STATIC_DIR);

	if(!fs.existsSync(staticDir)) {
		throw new Error(`Static directory at ${staticDir} does not exist.`);
	}
	
	const index = fs.readFileSync(staticDir + '/index.html', {encoding: 'UTF-8'});
	const dom = new JSDOM(index);
	const document = dom.window.document;
	document.querySelector('nav').innerHTML = nav;
	const serialized = dom.serialize();
	fs.writeFileSync(staticDir + '/index.html', serialized);
}


	
function createSmellsJson(files) {
	const smells = files.map((file) => {
		const relPath = path.relative('code-smells', file.parentPath);
		const pathParts = relPath.split(path.sep);
		const type = pathParts[0];
		const name = pathParts[1];
		const sanitizedType = cleanName(type);
		const sanitizedName = cleanName(name);
		const href = `code-smells/${type}/${name}`;
		return {
			name: sanitizedName,
			type: sanitizedType,
			href: href,
		}
	})

	// const grouped = smells.reduce((arr, smell) => {
	// 	if(!arr[smell.type]) {
	// 		arr[smell.type] = [];
	// 	}

	// 	arr[smell.type].push(smell);
	// 	return arr;
	// }, {});

	return smells;
}

function createNav(smells) {
	const nav = '<ul>' + smells.map((smell) => {
		const tag = `<li><a href='/${smell.href}'>${smell.type}/${smell.name}</a></li>`;
		return tag;
	}).join("") + '</ul>';

	// console.log(nav);
	
	const dom = new JSDOM(BOILERPLATE_HTML);
	const document = dom.window.document;
	document.querySelector("nav").innerHTML = nav;
	const injectedHTML = dom.serialize();
	
	addNavToIndex(nav);

	BOILERPLATE_HTML = injectedHTML;
	// console.log(BOILERPLATE_HTML);

	return nav;
}

async function parseDir() {
	const outDir = path.join(process.cwd(), "out");
	
	safeCreateDirSync(outDir);

	const files = fetchAllMarkdownFiles();
	
	const smells = createSmellsJson(files);

	createNav(smells);
	
	// TODO: Create code smell type directories first and then create HTML files.
	// Currently, for each code smell of a single type, it calls safeCreateDirSync 
	// which is unecessary.
	//
	// TODO: First go through the code-smell directory and fetch all the next folders list
	// and create directories.

	 files.forEach(file => {
		const relPath = path.relative('code-smells', file.parentPath);
		const pathParts = relPath.split(path.sep);
		const type = pathParts[0];
		const name = pathParts[1];
		const typePath = path.join(outDir, `code-smells/${type}`);
		safeCreateDirSync(outDir + '/code-smells');
		safeCreateDirSync(typePath);
		const content = fs.readFileSync(file.parentPath + "/" + file.name, {encoding: 'UTF-8'});
		const parsed = marked.parse(content);
		const filepath = typePath + '/' + name + '.html';
		const injectedHTML = injectHTML(capitalize(cleanName(name)), parsed);
		createHTMLFile(filepath, injectedHTML);
	 })
}

function capitalize(name: string) {
	const words = name.split(' ');
	const capitalized = words.map((word) => {
		return word.charAt(0).toUpperCase() + word.slice(1);;
	});
	// console.log(capitalized.join(' '));
	return capitalized.join(' ');
}


function cleanName(name: string) {
	const regex = new RegExp('-', 'g');
	const cleanName = name.replace(regex, ' ');
	return cleanName;
}

function fetchAllMarkdownFiles() {
	const contentDir = path.join(process.cwd(), CONTENT_DIR);
	
	if(!fs.existsSync(contentDir)) {
		throw new Error(`Content directory at ${contentDir} does not exist.`);
	}

	const files = fs.readdirSync(contentDir, {withFileTypes: true, recursive: true});
	const mdFiles = files.filter(file => file.name.endsWith(".md"));

	return mdFiles;
}
	

function safeCreateDirSync(pathname: string) {
	if(fs.existsSync(pathname)) {
		// console.log(`${pathname} already exists, skipping...`);
	} else {
		fs.mkdirSync(pathname);
	}
}

function createHTMLFile(pathname: string, content: string) {
	try {
		fs.writeFileSync(pathname, content);
		// console.log(`${pathname} written successfully`);
	} catch(e) {
		console.error(e);
	}
}

function injectHTML(name: string, html: string) {
	const dom = new JSDOM(BOILERPLATE_HTML);
	const document = dom.window.document;
	document.title = `${name} | Code Smell References`;
	document.querySelector("main").innerHTML = html;
	const injectedHTML = dom.serialize();
	return injectedHTML;
}

parseDir();
