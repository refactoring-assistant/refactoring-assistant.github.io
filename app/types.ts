export type CodeSmell = {
	name: string;
	type: string;
	href: string;
}

export type CodeSmells = {
    [type: string]: CodeSmell[];
}

export interface CodeBlockProps {
  code: string;
  language?: string;
}