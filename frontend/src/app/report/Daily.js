import { useState, useEffect } from "react";

import Empty from "./_components/Empty";
import formatDate from "@/utils/formatDate";
import getFetch from "@/services/getFetch";
import SleepTime from "./_components/SleepTime";
import SelectDate from "./_components/SelectDate";
import Score from "./_components/Score";
import Chart from "./_components/Chart";

export default function Daily() {
  const [report, setReport] = useState({});
  const [date, setDate] = useState(new Date());

  const getReport = async () => {
    const formattedDate = formatDate(date);
    try {
      const resp = await getFetch(`report/${formattedDate}`);

      console.log(resp.response);
      setReport(resp.response);
    } catch {
      setReport({});
    }
  };

  useEffect(() => {
    getReport();
  }, [date]);

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

      {report.date ? (
        <>
          <SleepTime report={report}></SleepTime>
          <Score report={report}></Score>
          <Chart sleepStages={report.sleepStages}></Chart>
        </>
      ) : (
        <Empty />
      )}
    </>
  );
}
