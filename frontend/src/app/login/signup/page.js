"use client";

import Link from "next/link";
import Image from "next/image";

import Title from "../Title";
import Button from "@/components/Button";
import leftArrow from "../../../../public/icons/left-arrow.png";
import styles from "./page.module.css";

export default function SingUp() {
  return (
    <div className={styles.container}>
      <Title>회원가입</Title>
      <form className={styles.input_container}>
        <div className={styles.input_content}>
          <label htmlFor="id">아이디</label>
          <input className={styles.input} id="id" type="text" />
        </div>
        <div className={styles.input_content}>
          <label htmlFor="id">비밀번호</label>
          <input className={styles.input} id="id" type="text" />
        </div>
        <div className={styles.input_content}>
          <label htmlFor="id">비밀번호 확인</label>
          <input className={styles.input} id="id" type="text" />
        </div>
        <div className={styles.input_content}>
          <label htmlFor="id">아이디</label>
          <input className={styles.input} id="id" type="text" />
        </div>
        <Button>회원가입</Button>
      </form>
    </div>
  );
}
