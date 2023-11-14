import Image from "next/image";

import { useState, useEffect } from "react";

import styles from "./SleepTime.module.css";
import sun from "#/icons/report/sun.png";
import clock from "#/icons/report/clock.png";
import moon from "#/icons/report/moon.png";

export default function SleepTime(props) {
  const [sleepTime, setSleepTime] = useState("");
  const [wakeUpTime, setWakeUpTime] = useState("");
  const [bedTime, setBedTime] = useState("");

  const calculSleepTime = () => {
    if (!props.report.date) {
      return;
    }

    const hours = Number.parseInt(props.report.sleepDuration / 60);
    const minutes = props.report.sleepDuration % 60;
    setSleepTime(`${hours}h ${minutes}m`);

    props.report.sleepStages.forEach((sleepStage, idx) => {
      if (idx === 0) {
        const dateObject = new Date(sleepStage.startTime);
        const hours = dateObject.getHours();
        const minutes = dateObject.getMinutes();

        setBedTime(`${hours} : ${minutes}`);
      }

      if (idx === props.report.sleepStages.length - 1) {
        const dateObject = new Date(sleepStage.endTime);
        const hours = dateObject.getHours();
        const minutes = dateObject.getMinutes();

        setWakeUpTime(`${hours} : ${minutes}`);
      }
    });
  };

  useEffect(() => {
    calculSleepTime();
  }, [props]);

  return (
    <div className={styles.layout}>
      <div className={styles.container}>
        <div className={styles.content}>
          <div className={styles.title}>수면 시간</div>
          <div className={styles.time}>
            <Image src={clock} alt="시계"></Image>
            {sleepTime}
          </div>
        </div>

        <div className={styles.content}>
          <div className={styles.title}>취침 시간</div>
          <div className={styles.time}>
            <Image src={moon} alt="달"></Image>
            {bedTime}
          </div>
        </div>

        <div className={styles.content}>
          <div className={styles.title}>기상 시간</div>
          <div className={styles.time}>
            <Image src={sun} alt="해"></Image>
            {wakeUpTime}
          </div>
        </div>
      </div>
    </div>
  );
}
