import Image from "next/image";
import TrainInner from "public/assets/image/train-inner.png";
import styles from "./page.module.css";

export default function Home() {
  return (
    <div className={styles.container}>
      <Image className={styles.train_inner} src={TrainInner} alt="기차 내부" />
    </div>
  );
}
