export function capitalize(name: string) {
	const words = name.split(' ');
	const capitalized = words.map((word) => {
		return word.charAt(0).toUpperCase() + word.slice(1);;
	});
	return capitalized.join(' ');
}

export function cleanName(name: string) {
	const regex = new RegExp('-', 'g');
	const cleanName = name.replace(regex, ' ');
	return cleanName;
}
