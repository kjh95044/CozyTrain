import Image from "next/image";

import train from "#/icons/train64.png";
import styles from "./Score.module.css";

export default function Score() {
  return (
    <div className={styles.layout}>
      <div>점수: 67점</div>
      <div className={styles.width}>
        <div className={styles.score}>
          이동 거리: 67km
          <Image className={styles.train} src={train} alt="기차"></Image>
        </div>
      </div>
      <div className={styles.content}>
        저희가 생각한 어제 수면 점수가 낮은 이유는 카페인 과다 섭취 입니다.
        앞으로 카페인 섭취를 줄이고 운동량을 신경쓰는 건 어떤가요?
      </div>
    </div>
  );
}
