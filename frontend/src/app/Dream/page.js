"use client";

import "react-calendar/dist/Calendar.css";
import styles from "./page.module.css";
import "./page.css";
import NavBottom from "@/components/NavBottom";
import Link from "next/link";
import getFetch from "@/services/getFetch";
import { useEffect, useState } from "react";
import moment from "moment";
import Image from "next/image";
import Calendar from "react-calendar";
import PrimaryButton from "@/components/button/PrimaryButton";

export default function Dream() {
  const [dreams, setDreams] = useState([]);

  const getDreams = async () => {
    const data = await getFetch("dream");
    setDreams(data.response.dreamDtoResList);
  };

  useEffect(() => {
    getDreams();
  }, []);

  // 캘린더 관련
  const [today, setToday] = useState(new Date()); // 현재 날짜
  const [nowDate, onChange] = useState(today); //선택한 날짜
  const monthOfActiveDate = moment(nowDate).format("YYYY-MM"); //보여지는 연도=월
  const [nowMonth, setNowMonth] = useState(monthOfActiveDate); //보여지는 달

  const getNowMonth = (day) => {
    const newMonth = moment(day).format("YYYY-MM");
    setNowMonth(newMonth);
  };

  const isDateAfterToday = (date) => {
    return date > today;
  };

  // 날짜에 이모지 추가
  const addContent = ({ date }) => {
    const result = dreams.find(
      (day) => day.dreamDate === moment(date).format("YYYY-MM-DD")
    );
    if (result) {
      return (
        <>
          <Image
            src={`/images/${result.dreamType}.svg`}
            width="26"
            height="26"
            alt="달력"
          />
        </>
      );
    }
    return moment(date).format("D");
  };

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <b>꿈</b>
      </div>
      <div className={styles.note_container}>
        <Calendar
          locale="ko" //한글
          onChange={onChange}
          value={today} //선택한 날짜
          prev2Label={null} //이전 연도 누르는 버튼 안 보이게
          next2Label={null} //다음 연도 누르는 버튼 안 보이게
          showNeighboringMonth={false} //이번 달 날짜만 보이게
          //지금 보이는 년/월/일이 바뀔 때마다 실행
          onActiveStartDateChange={({ activeStartDate }) =>
            getNowMonth(activeStartDate)
          }
          //오늘 이후 날짜 연하게
          tileClassName={({ date, view }) => {
            if (view === "month" && isDateAfterToday(date)) {
              return "after-today";
            }
            return null;
          }}
          tileContent={addContent} //타일 안에 보여줄 컨텐츠
        />
      </div>
      <div className={styles.next_btn}>
        {/* 오늘 꿈 있으면 안 보이게..? */}
        <Link href={`/dream/new`}>
          <div className={styles.next_txt}>오늘 꾼 꿈 적으러 가기</div>
        </Link>
      </div>
      <NavBottom />
    </div>
  );
}
