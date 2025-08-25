import {JSDOM} from "jsdom";
import path from "path";
import {marked} from "marked";
import * as fs from 'node:fs';

import { createNav, injectHTML } from './utils/html-utils.ts';
import { capitalize, cleanName } from './utils/utils.ts';
import { safeCreateDirSync, createHTMLFile, fetchAllMarkdownFiles } from './utils/file-utils.ts';
import { type CodeSmell } from './types.ts';

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

	return smells;
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

parseDir();
