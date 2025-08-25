import {JSDOM} from "jsdom";
import path from "path";
import {marked} from "marked";
import * as fs from 'node:fs';

const CONTENT_DIR = 'code-smells';

const BOILERPLATE_HTML = 
	`
		<!DOCTYPE html>
		<html lang='en'>
			<head>
				<meta charset="UTF-8"/>
				<title></title>
				<meta name="viewport" content="width=device-width,initial-scale=1" />
			</head>
			<body>
				<nav>
				</nav>
				<main>
				</main>
			</body>
		</html
	`

async function parseDir() {
	const outDir = path.join(process.cwd(), "out");
	
	safeCreateDirSync(outDir);

	const files = fetchAllMarkdownFiles();

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
		const injectedHTML = injectHTML(name, parsed);
		createHTMLFile(filepath, injectedHTML);
	 })
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
		console.log(`${pathname} already exists, skipping...`);
	} else {
		fs.mkdirSync(pathname);
	}
}

function createHTMLFile(pathname: string, content: string) {
	try {
		fs.writeFileSync(pathname, content);
		console.log(`${pathname} written successfully`);
	} catch(e) {
		console.error(e);
	}
}

function injectHTML(name: string, html: string) {
	const dom = new JSDOM(BOILERPLATE_HTML);
	const document = dom.window.document;
	document.querySelector("main").innerHTML = html;
	const injectedHTML = dom.serialize();
	return injectedHTML;
}

parseDir();
