import Image from "next/image";

import clouds from "../../public/images/Clouds.png";
import styles from "./Background.module.css";

export default function Background() {
  return (
    <div className={styles.container}>
      <Image className={styles.clouds} src={clouds} alt="구름"></Image>
    </div>
  );
}
