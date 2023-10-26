import Link from "next/link";
import Image from "next/image";

import TrainInner from "public/images/train-inner.png";
import styles from "./page.module.css";

export default function Home() {
  return (
    <div className={styles.container}>
      <Link href="/dream">가보자</Link>
      <Link href="login">로그인 화면</Link>
    </div>
  );
}
