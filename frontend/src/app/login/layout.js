import Image from "next/image";

import styles from "./layout.module.css";
import Clouds from "/public/images/Clouds.png";

export default function Layout({ children }) {
  return (
    <div className={styles.container}>
      <Image className={styles.clouds} src={Clouds} alt="구름"></Image>
      {children}
    </div>
  );
}
