import { Highlight, themes } from "prism-react-renderer";
import { CodeBlockProps } from "../types";

export default function CodeBlock({ code, language = 'java' }: CodeBlockProps) {

  if (!code) {
    return <pre>No code available</pre>;
  }

  return (
    <Highlight
      theme={themes.vsDark}
      code={code}
      language={language}
    >
      {({ className, style, tokens, getLineProps, getTokenProps }) => (
        <pre style={style} className={className}>
          {tokens.map((line, i) => (
            <div key={i} {...getLineProps({ line })}>
              <span>{String(i+1).padStart(2, '0')}</span>
              {line.map((token, key) => (
                <span key={key} {...getTokenProps({ token })} />
              ))}
            </div>
          ))}
        </pre>
      )}
    </Highlight>
  );
}
