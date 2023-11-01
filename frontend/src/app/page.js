import Link from "next/link";
import Image from "next/image";

import InTrain from "public/images/intrain.jpg";
import styles from "./page.module.css";

export default function Home() {
  return (
    <div className={styles.container}>
      <Image className={styles.inTrain} src={InTrain} alt="기차 내부"></Image>
    </div>
  );
}
