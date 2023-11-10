import { useState } from "react";

import getFetch from "@/services/getFetch";
import SleepTime from "./_components/SleepTime";
import SelectDate from "./_components/SelectDate";
import Score from "./_components/Score";
import Stage from "./_components/Stage";

export default function Daily() {
  // const [report, setReport] = useState({});
  const [date, setDate] = useState(new Date());

  const getReport = async () => {
    const resp = await getFetch("report");

    console.log(resp);

    // setReport(resp.response);
  };

  // useEffect(() => {
  //   getReport();
  // }, []);

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
      <SleepTime></SleepTime>
      <Score></Score>
      <Stage></Stage>
    </>
  );
}
