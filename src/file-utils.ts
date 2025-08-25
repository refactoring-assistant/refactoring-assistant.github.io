import * as fs from 'node:fs';
import path from 'path';

const CONTENT_DIR = 'code-smells';

export function safeCreateDirSync(pathname: string) {
	if(fs.existsSync(pathname)) {
		console.log(`${pathname} already exists, skipping...`);
	} else {
		fs.mkdirSync(pathname);
	}
}

export function createHTMLFile(pathname: string, content: string) {
	try {
		fs.writeFileSync(pathname, content);
		console.log(`${pathname} written successfully`);
	} catch(e) {
		console.error(e);
	}
}

export function fetchAllMarkdownFiles() {
	const contentDir = path.join(process.cwd(), CONTENT_DIR);
	
	if(!fs.existsSync(contentDir)) {
		throw new Error(`Content directory at ${contentDir} does not exist.`);
	}

	const files = fs.readdirSync(contentDir, {withFileTypes: true, recursive: true});
	const mdFiles = files.filter(file => file.name.endsWith(".md"));

	return mdFiles;
}
