"use client";

import Image from "next/image";
import { useState } from "react";

import Weekly from "./Weekly";
import Daily from "./Daily";
import styles from "./page.module.css";
import graph from "#/images/graph.png";
import BackGround from "@/components/Background";
import NavBottom from "@/components/NavBottom";
import Header from "@/components/Header";

export default function Report() {
  const [isDaily, setIsDaily] = useState(true);

  return (
    <div className={styles.layout}>
      <BackGround></BackGround>
      <Header>리포트</Header>
      <div className={styles.switch} onClick={() => setIsDaily((e) => !e)}>
        <Image src={graph} alt="주일 교체"></Image>
        {isDaily ? "일간" : "주간"}
      </div>

      {isDaily && <Daily></Daily>}
      {!isDaily && <Weekly></Weekly>}

      <NavBottom />
    </div>
  );
}
