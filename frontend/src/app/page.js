import Image from "next/image";
import TrainInner from "public/images/train-inner.png";
import styles from "./page.module.css";

export default function Home() {
  return (
    <div className={styles.container}>
      <a href="/Dream">가보자</a>
    </div>
  );
}
