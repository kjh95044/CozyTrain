import Link from "next/link";
import Image from "next/image";

import NavLeft from "@/components/NavLeft";
import inTrain from "public/images/intrain.jpg";
import styles from "./page.module.css";

export default function Home() {
  return (
    <div>
      <NavLeft />
      <Image className={styles.inTrain} src={inTrain} alt="기차 내부"></Image>
    </div>
  );
}
