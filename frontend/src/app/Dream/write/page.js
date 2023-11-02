"use client";

import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import PrimaryButton from "@/components/button/PrimaryButton";
import { useSearchParams } from "next/navigation";
import { useState } from "react";
import Fetch from "@/services/post";

export default function Wrtie() {
  const today = new Date();
  const year = today.getFullYear();
  const month = today.getMonth() + 1;
  const day = today.getDate();
  const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
  const dayOfWeek = daysOfWeek[today.getDay()];
  const date = `${year}년 ${month}월 ${day}일 ${dayOfWeek}요일`;

  let dreamType = useSearchParams().get("dream");

  const [content, setContent] = useState("");

  const contentChange = (event) => {
    setContent(event.target.value);
  };

  const handleSubmit = async (e) => {
    const postData = {
      dreamContent: content,
      dreamType: dreamType,
    };

    const respData = await Fetch("dream", postData);
  };

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <b>꿈</b>
      </div>

      <div className={styles.note_container}>
        <div className={styles.title}>
          <b>{date}</b>
        </div>
        <textarea
          name="content"
          placeholder="내용"
          onChange={contentChange}
          className={styles.content}
        />
        <div className={styles.write_btn}>
          <div onClick={handleSubmit}>
            <PrimaryButton>등록</PrimaryButton>
          </div>
        </div>
      </div>

      <NavBottom />
    </div>
  );
}
