import Link from "next/link";
import Navbar from "./components/Navbar";

export default function Home() {
  return (
    <div>
      <Navbar />
      <main className="home">
        <h1>Code Smells Reference</h1>
        <p>Welcome to the code smells reference website.</p>
        <p>To get started, click any code smell in the navbar.</p>
        <hr/>
        <section>
          <p>For any queries, please contact</p>
          <ul>
            <li>Layla Dong at <Link href="mailto:dong.pa@northeastern.edu">dong.pa@northeastern.edu</Link></li>
            <li>Yash Mahesh Burshe at <Link href="mailto:burshe.y@northeastern.edu">burshe.y@northeastern.edu</Link></li>
          </ul>
        </section>
      </main>
    </div>
  );
}
