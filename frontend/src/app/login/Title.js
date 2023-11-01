import Link from "next/link";
import Image from "next/image";

import leftArrow from "#/icons/left-arrow.png";
import styles from "./Title.module.css";

export default function Title({ children }) {
  return (
    <div className={styles.title}>
      <Link className={styles.back} href="/login">
        <Image src={leftArrow} alt="뒤로가기"></Image>
      </Link>
      {children}
    </div>
  );
}
