import Image from "next/image";

import Home from "public/icons/home.png";

export default function Navigation() {
  return (
    <nav style={{ position: "absolute", bottom: "0" }}>
      <ul>
        <li>
          <Image src={Home} alt="홈" />
        </li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
      </ul>
    </nav>
  );
}
