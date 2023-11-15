import { useState, useEffect } from "react";

import getFetch from "@/services/getFetch";
import styles from "./LetterContent.module.css";
import SleepTime from "./report/_components/SleepTime";
import Empty from "./report/_components/Empty";
import Score from "./report/_components/Score";

export default function LetterContent() {
  const [averageReport, setAverageReport] = useState({});
  const [isLoading, setIsLoading] = useState(true);
  const [report, setReport] = useState({});
  const [date, setDate] = useState([]);

  useEffect(() => {
    getReport();
  }, []);

  const getReport = async () => {
    try {
      const resp = await getFetch(`report`);

      setDate(resp.response.todayReport.date.split("-"));
      setReport(resp.response.todayReport);
      setAverageReport(resp.response.averageReport);
    } catch (e) {
      console.log(e);
      setReport({});
    }

    setIsLoading(false);
  };

  return (
    <div className={styles.container}>
      <div className={styles.title}>
        {date[1]}월 {date[2]}일 리포트
      </div>
      {!isLoading && report.date && (
        <>
          <SleepTime report={report}></SleepTime>
          <Score report={report} averageReport={averageReport}></Score>
        </>
      )}

      {!isLoading && !report.date && <Empty />}
    </div>
  );
}
