"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

import fetchPost from "@/services/postFetch";
import Title from "../Title";
import styles from "./page.module.css";

export default function SingUp() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
  const [username, setUsername] = useState("");
  const router = useRouter();

  const CheckDuplicateId = (e) => {
    e.preventDefault();
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = {
      memberId: id,
      memberPassword: password,
      memberName: username,
    };

    fetchPost("member/signup", formData)
      .then((resp) => {
        console.log("로그인 성공, " + resp);
        router.push("/login");
      })
      .catch((e) => console.log(e));
  };

  return (
    <div className={styles.container}>
      <Title>회원가입</Title>
      <form className={styles.input_container}>
        <div className={styles.input_content}>
          <label htmlFor="id">아이디</label>
          <input
            className={styles.input}
            id="id"
            name="id"
            type="text"
            value={id}
            onChange={(e) => setId(e.target.value)}
          />
          <button className={styles.id_check_button} onClick={CheckDuplicateId}>
            확인
          </button>
        </div>
        <div className={styles.input_content}>
          <label htmlFor="password">비밀번호</label>
          <input
            className={styles.input}
            id="password"
            name="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className={styles.input_content}>
          <label htmlFor="passwordCheck">비밀번호 확인</label>
          <input
            className={styles.input}
            id="passwordCheck"
            type="password"
            value={passwordCheck}
            onChange={(e) => setPasswordCheck(e.target.value)}
          />
        </div>
        <div className={styles.input_content}>
          <label htmlFor="username">닉네임</label>
          <input
            className={styles.input}
            id="username"
            name="username"
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className={styles.button_container}>
          <button
            className={styles.button}
            type="submit"
            onClick={handleSubmit}
          >
            회원가입
          </button>
        </div>
      </form>
    </div>
  );
}
