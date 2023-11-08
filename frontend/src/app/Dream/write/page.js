"use client";

import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import PrimaryButton from "@/components/button/PrimaryButton";
import postFetch from "@/services/postFetch";

import { useSearchParams } from "next/navigation";
import { useState } from "react";
import { useRouter } from "next/navigation";
import Header from "@/components/Header";

export default function Write() {
  const today = new Date();
  const year = today.getFullYear();
  const month = today.getMonth() + 1;
  const day = today.getDate();
  const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
  const dayOfWeek = daysOfWeek[today.getDay()];
  const date = `${year}년 ${month}월 ${day}일 ${dayOfWeek}요일`;

  let dreamType = useSearchParams().get("dream");

  const router = useRouter();
  const [content, setContent] = useState("");

  const contentChange = (event) => {
    setContent(event.target.value);
  };

  const handleSubmit = async (e) => {
    const postData = {
      dreamContent: content,
      dreamType: dreamType,
    };

    const respData = await postFetch("dream", postData);
    if (respData.success) {
      router.push("/dream");
    }
  };

  return (
    <div className={styles.container}>
      <Header>꿈</Header>

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
