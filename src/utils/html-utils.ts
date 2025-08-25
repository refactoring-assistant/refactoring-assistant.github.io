import { JSDOM } from 'jsdom';
import path from "path";
import * as fs from 'node:fs';

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

export function createNav(smells) {
	const nav = '<ul>' + smells.map((smell) => {
		const tag = `<li><a href='/${smell.href}'>${smell.type}/${smell.name}</a></li>`;
		return tag;
	}).join("") + '</ul>';
	
	const dom = new JSDOM(BOILERPLATE_HTML);
	const document = dom.window.document;
	document.querySelector("nav").innerHTML = nav;
	const injectedHTML = dom.serialize();
	
	addNavToIndex(nav);

	BOILERPLATE_HTML = injectedHTML;

	return nav;
}


export function injectHTML(name: string, html: string) {
	const dom = new JSDOM(BOILERPLATE_HTML);
	const document = dom.window.document;
	document.title = `${name} | Code Smell References`;
	document.querySelector("main").innerHTML = html;
	const injectedHTML = dom.serialize();
	return injectedHTML;
}
