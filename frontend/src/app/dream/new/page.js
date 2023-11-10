"use client";

import Image from "next/image";
import coin from "/public/images/0.svg";
import food from "/public/images/1.svg";
import friend from "/public/images/2.svg";
import poo from "/public/images/3.svg";
import nightmare from "/public/images/4.svg";
import sheep from "/public/images/5.svg";
import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import { useState } from "react";
import PrimaryButton from "@/components/button/PrimaryButton";
import Link from "next/link";
import Header from "@/components/Header";

export default function New() {
  const [dream, setDream] = useState(0);
  const [img, setImg] = useState();
  const dreams = ["돈/재물", "음식", "지인", "똥", "악몽", "기타"];

  const images2 = [coin, food, friend, poo, nightmare, sheep];

  return (
    <div className={styles.container}>
      <Header>꿈</Header>
      <div className={styles.note_container}>
        <div className={styles.title}>
          오늘의 <b>꿈</b>을 골라보세요
        </div>
        <div className={styles.grid}>
          {dreams.map((item, i) => (
            <div
              key={i}
              className={`${styles.btn_group} ${
                dream === i ? styles.btn_click : ""
              }`}
              onClick={() => {
                setDream(i);
                setImg(images2[i]);
              }}
            >
              <Image
                src={images2[i]}
                alt="dream_img"
                width={100}
                height={100}
                className={styles.btn_img}
              ></Image>
              {item}
            </div>
          ))}
        </div>
        <div className={styles.next_btn}>
          <Link href={`/dream/write?dream=${dream}`}>
            <PrimaryButton>다음</PrimaryButton>
          </Link>
        </div>
      </div>
      <NavBottom />
    </div>
  );
}
