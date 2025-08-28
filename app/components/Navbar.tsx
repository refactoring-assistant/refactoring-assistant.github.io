import Link from "next/link";
import { CodeSmells } from "../types";
import { createSmellsJson } from "../utils";

export default async function Navbar() {
    const smells: CodeSmells = createSmellsJson();

    return (
        <div className="nav-wrapper">
            <Link className="title" href="/">Code Smells Reference</Link>
            <nav>
                {
                    Object.entries(smells).map(([type, smells]) => (
                        <ul key={type}>
                            <h3>{type}</h3>
                            {smells.map(smell => (
                                <li key={smell.href}>
                                    <Link href={smell.href}>{smell.name}</Link>
                                </li>
                            ))}
                        </ul>
                    ))
                }
            </nav>
        </div>

    )

}