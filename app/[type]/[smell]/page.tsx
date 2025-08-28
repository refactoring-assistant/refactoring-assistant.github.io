import fs from 'fs';
import path from 'path';
import { marked } from 'marked';
import Navbar from '@/app/components/Navbar';
import { Metadata } from 'next';
import { cleanName, capitalize, readFilesSafely } from '@/app/utils';
import CodeTab from '@/app/components/CodeTab';

interface Props {
  params: { type: string; smell: string };
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { type, smell } = await params;
  const cleanedName = capitalize(cleanName(smell));
  const cleanedType = capitalize(cleanName(type));
  return {
    title: `${cleanedName} - ${cleanedType} | Code Smells Reference`,
    description: `Learn more about the ${cleanedName} code smell in the context of ${cleanedType}.`,
  };
}

export async function generateStaticParams() {
  const typesFolder = path.join(process.cwd(), 'code-smells');
  const types = fs.readdirSync(typesFolder);

  const params: { type: string; smell: string }[] = [];

  for (const type of types) {
    const typePath = path.join(typesFolder, type);
    if (!fs.statSync(typePath).isDirectory()) continue;

    const smells = fs.readdirSync(typePath).filter(f =>
      fs.statSync(path.join(typePath, f)).isDirectory()
    );

    for (const smell of smells) {
      params.push({ type, smell });
    }
  }

  return params;
}

function getExamples(folderPath: string) {
  const exampleOnePath = path.join(folderPath, 'code-smell-files/example1');
  let exampleTwoPath = path.join(folderPath, 'code-smell-files/example2');
  const ignoreExampleTwoPath = path.join(folderPath, 'code-smell-files/ignore-example2');
  if (fs.existsSync(ignoreExampleTwoPath)) {
    exampleTwoPath = ignoreExampleTwoPath;
  }

  const beforeExampleOneContents = readFilesSafely(exampleOnePath, 'BE1.java');
  const afterExampleOneContents = readFilesSafely(exampleOnePath, 'GE1.java');
  const beforeExampleTwoContents = readFilesSafely(exampleTwoPath, 'BE2.java');
  const afterExampleTwoContents = readFilesSafely(exampleTwoPath, 'GE2.java');

  return {
    beforeExampleOneContents,
    afterExampleOneContents,
    beforeExampleTwoContents,
    afterExampleTwoContents
  }
}

export default async function SmellPage({ params }: Props) {
  const { type, smell } = await params;


  const folderPath = path.join(process.cwd(), 'code-smells', type, smell);

  const { beforeExampleOneContents, afterExampleOneContents, beforeExampleTwoContents, afterExampleTwoContents } = getExamples(folderPath)

  const mdFile = fs.readdirSync(folderPath).filter(f => f.endsWith('.md'))[0];

  const content = fs.readFileSync(path.join(folderPath, mdFile), 'utf-8');
  const html = marked.parse(content);

  return (
    <div>
      <Navbar />
      <main>
        <section>
          <div dangerouslySetInnerHTML={{ __html: html }} />
        </section>
        <section>
          <CodeTab
            beforeExampleOneContents={beforeExampleOneContents}
            afterExampleOneContents={afterExampleOneContents}
            beforeExampleTwoContents={beforeExampleTwoContents}
            afterExampleTwoContents={afterExampleTwoContents}
          />
        </section>
      </main>
    </div>
  );
}
