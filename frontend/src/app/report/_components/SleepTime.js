import Image from "next/image";

import { useState, useEffect } from "react";

import styles from "./SleepTime.module.css";
import sun from "#/icons/report/sun.png";
import clock from "#/icons/report/clock.png";
import moon from "#/icons/report/moon.png";

export default function SleepTime(props) {
  const [sleepTime, setSleepTime] = useState("");

  const calculSleepTime = () => {
    if (!props.report.date) {
      setSleepTime("");
      return;
    }

    const hours = Number.parseInt(props.report.sleepDuration / 60);
    const minutes = props.report.sleepDuration % 60;
    setSleepTime(`${hours} : ${minutes}`);
  };

  useEffect(() => {
    calculSleepTime();
  }, [props]);

  return (
    <div className={styles.layout}>
      <div className={styles.container}>
        <div className={styles.content}>
          <div>수면 시간</div>
          <div className={styles.time}>
            <Image src={clock} alt="시계"></Image>
            {sleepTime}
          </div>
        </div>

        <div className={styles.content}>
          <div>취침 시간</div>
          <div className={styles.time}>
            <Image src={moon} alt="달"></Image>11 : 53
          </div>
        </div>

        <div className={styles.content}>
          <div>기상 시간</div>
          <div className={styles.time}>
            <Image src={sun} alt="해"></Image>06 : 03
          </div>
        </div>
      </div>
    </div>
  );
}
