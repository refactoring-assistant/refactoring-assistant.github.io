import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Code Smells Reference",
  description: "Extended reference for code smells with examples",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        {children}
      </body>
    </html>
  );
}
