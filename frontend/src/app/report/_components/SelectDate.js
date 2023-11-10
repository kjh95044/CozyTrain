import Image from "next/image";

import styles from "./SelectDate.module.css";
import leftArrow from "#/icons/left-arrow.png";
import rightArrow from "#/icons/right-arrow.png";

export default function SelectDate(props) {
  return (
    <div className={styles.container}>
      <Image src={leftArrow} alt="전날" onClick={props.setDatePrev}></Image>
      <div className={styles.title}>
        {props.date.getMonth() + 1}월 {props.date.getDate()}일
      </div>
      <Image src={rightArrow} alt="다음날" onClick={props.setDateNext}></Image>
    </div>
  );
}
