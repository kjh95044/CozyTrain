"use client";

import Image from "next/image";
import { useState } from "react";

import Day from "./Day";
import styles from "./page.module.css";
import graph from "#/images/graph.png";
import BackGround from "@/components/Background";
import NavBottom from "@/components/NavBottom";
import Header from "@/components/Header";

export default function Report() {
  const [isDay, setIsDay] = useState(true);

  return (
    <div>
      <BackGround></BackGround>
      <Header>리포트</Header>
      <div className={styles.switch}>
        <Image src={graph} alt="주일 교체"></Image>
        일간
      </div>

      {isDay ? <Day /> : <></>}

      <NavBottom />
    </div>
  );
}
