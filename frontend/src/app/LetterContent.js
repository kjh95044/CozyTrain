import { useState, useEffect } from "react";

import useStore from "@/store/useStore";
import getFetch from "@/services/getFetch";
import styles from "./LetterContent.module.css";
import SleepTime from "./report/_components/SleepTime";
import Empty from "./report/_components/Empty";
import Score from "./report/_components/Score";

export default function LetterContent() {
  const [averageReport, setAverageReport] = useState({});
  const [isLoading, setIsLoading] = useState(true);
  const [report, setReport] = useState({});
  const [date, setDate] = useState(new Date());
  const { setLocation } = useStore();

  useEffect(() => {
    getReport();
    getCurLocation();
  }, []);

  const getCurLocation = async () => {
    const resp = await getFetch("train/cur-location-info");
    const location = resp.response;

    switch (location.region) {
      case "seoul":
        setLocation("한국 서울", location.dist);
        break;
      case "busan":
        setLocation("한국 부산", location.dist);
        break;
      case "jeju":
        setLocation("한국 제주도", location.dist);
        break;
      case "sapporo":
        setLocation("일본 삿포로", location.dist);
        break;
      case "tokyo":
        setLocation("일본 도쿄", location.dist);
        break;
      case "osaka":
        setLocation("일본 오사카", location.dist);
        break;
      default:
        setLocation("중국..", location.dist);
        break;
    }
  };

  const getReport = async () => {
    try {
      const resp = await getFetch(`report`);

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
        {date.getMonth() + 1}월 {date.getDate()}일 리포트
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
