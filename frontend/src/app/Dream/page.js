"use client";

import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import PrimaryButton from "@/components/button/PrimaryButton";
import Link from "next/link";
import getFetch from "@/services/getFetch";
import { useEffect, useState } from "react";

export default function Dream() {
  const [dreams, setDreams] = useState();

  const getDreams = async () => {
    console.log("getDreams");
    const data = await getFetch("dream");
    setDreams(data.response.dreamDtoResList);
  };

  useEffect(() => {
    getDreams();
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <b>꿈</b>
      </div>
      <div className={styles.note_container}>
        <div className={styles.next}>
          {/* 오늘 꿈 있으면 안 보이게..? */}
          <Link href={`/dream/new`}>
            <PrimaryButton>오늘의 꾼 꿈 적으러 가기</PrimaryButton>
          </Link>
        </div>
      </div>
      <NavBottom />
    </div>
  );
}
