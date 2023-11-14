import Image from "next/image";

import cloud from "../../public/images/cloud.png";
import styles from "./Background.module.css";

export default function Background() {
  return (
    <div className={styles.container}>
      <Image className={styles.cloud} src={cloud} alt="구름" priority></Image>
    </div>
  );
}
