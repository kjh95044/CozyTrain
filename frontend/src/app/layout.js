import "./globals.css";

import styles from "./layout.module.css";
import Navigation from "@/components/Navigation";

export const metadata = {
  title: "칙칙 포근포근",
  description: "기차 여행과 함께하는 수면 서포트 서비스",
};

export default function RootLayout({ children }) {
  return (
    <html>
      <body>
        {children}
        <Navigation />
      </body>
    </html>
  );
}
