import Link from "next/link";
import { redirect } from "next/navigation"

export default function NotFound() {

    redirect("/");

    return (
        <div>
            <p>Error 404 | Page not found. If you were not redirected, click <Link href="/">here</Link>.</p>
        </div>
    )
}