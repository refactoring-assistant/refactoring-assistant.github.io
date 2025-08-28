import path from "path";
import * as fs from 'node:fs'
import { CodeSmells } from "./types";

const CONTENT_DIR = 'code-smells';

export function cleanName(name: string) {
	const regex = new RegExp('-', 'g');
	const cleanName = name.replace(regex, ' ');
	return cleanName;
}

export function capitalize(name: string) {
	const words = name.split(' ');
	const capitalized = words.map((word) => {
		return word.charAt(0).toUpperCase() + word.slice(1);;
	});
	return capitalized.join(' ');
}

export function readFilesSafely(folder: string, endsWith: string) {
    if (!fs.existsSync(folder)) return [];
    const files = fs.readdirSync(folder).filter(f => f.endsWith(endsWith));
    if (!files.length) return [];
    return files.map(f => {
      const filePath = path.join(folder, f);
      return fs.existsSync(filePath) ? fs.readFileSync(filePath, 'utf-8') : '';
    });
  }

export function createSmellsJson() {
    const contentDir = path.join(process.cwd(), CONTENT_DIR);

	if(!fs.existsSync(contentDir)) {
		throw new Error(`Content directory at ${contentDir} does not exist.`);
	}

	const files = fs.readdirSync(contentDir, {withFileTypes: true, recursive: true});
	const mdFiles = files.filter(file => file.name.endsWith(".md"));

	const smells = mdFiles.map((file) => {
		const relPath = path.relative('code-smells', file.parentPath);
		const pathParts = relPath.split(path.sep);
		const type = pathParts[0];
		const name = pathParts[1];
		const sanitizedType = cleanName(type);
		const sanitizedName = cleanName(name);
		const href = `/${type}/${name}`;
		return {
			name: sanitizedName,
			type: sanitizedType,
			href: href,
		}
	});

	const grouped: CodeSmells = {};

	for (const smell of smells) {
		if (!grouped[smell.type]) {
			grouped[smell.type] = [];
		}
		grouped[smell.type].push(smell);
	}
    
	return grouped;
}