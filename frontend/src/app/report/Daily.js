"use client";

import { useState, useEffect } from "react";

import styles from "./Daily.module.css";
import Empty from "./_components/Empty";
import formatDate from "@/utils/formatDate";
import getFetch from "@/services/getFetch";
import SleepTime from "./_components/SleepTime";
import SelectDate from "./_components/SelectDate";
import Score from "./_components/Score";
import Walking from "@/components/Lottie/Walking";
import Coffee from "@/components/Lottie/Coffee";
import Meditation from "@/components/Lottie/Meditation";

export default function Daily() {
  const [averageReport, setAverageReport] = useState({});
  const [isLoading, setIsLoading] = useState(true);
  const [report, setReport] = useState({});
  const [date, setDate] = useState(new Date());
  const [factor, setFactor] = useState(null);

  useEffect(() => {
    getReport();
    getAverageReport();
  }, [date]);

  const getReport = async () => {
    const formattedDate = formatDate(date);
    try {
      const resp = await getFetch(`report/${formattedDate}`);
      setReport(resp.response);
    } catch {
      setReport({});
    }
  };

  const getAverageReport = async () => {
    try {
      const resp = await getFetch(`report`);

      setAverageReport(resp.response.averageReport);
    } catch (e) {
      console.log(e);
    }

    setIsLoading(false);
  };

  const handleSetFactor = (idx) => {
    setFactor(idx);
  };

  const setDatePrev = () => {
    const newDate = new Date(date);
    newDate.setDate(date.getDate() - 1);

    setDate(newDate);
  };

  const setDateNext = () => {
    const newDate = new Date(date);
    newDate.setDate(date.getDate() + 1);

    if (newDate.getTime() > new Date().getTime()) return;

    setDate(newDate);
  };

  return (
    <>
      <SelectDate
        date={date}
        setDatePrev={setDatePrev}
        setDateNext={setDateNext}
      ></SelectDate>

      {!isLoading && report.date && (
        <>
          <SleepTime report={report}></SleepTime>
          <Score
            report={report}
            averageReport={averageReport}
            handleSetFactor={handleSetFactor}
          ></Score>
          <div className={styles.lottie}>
            {factor === 0 && <Coffee></Coffee>}
            {factor === 1 && <Walking></Walking>}
            {factor === 2 && <Meditation></Meditation>}
          </div>
        </>
      )}

      {!isLoading && !report.date && <Empty />}
    </>
  );
}
