"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

import Title from "../Title";
import Button from "@/components/Button";
import styles from "./page.module.css";

export default function SingUp() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const router = useRouter();

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = {
      memberId: id,
      memberPassword: password,
      memberName: username,
    };

    fetch("https://dev.cozytrain.com/api/member/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    })
      .then((resp) => resp.json())
      .then(() => {
        router.push("/login");
      })
      .catch((error) => console.log(error));
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
            onChange={(e) => setId(e.target.value)}
          />
        </div>
        <div className={styles.input_content}>
          <label htmlFor="password">비밀번호</label>
          <input
            className={styles.input}
            id="password"
            name="password"
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {/* <div className={styles.input_content}>
          <label htmlFor="id">비밀번호 확인</label>
          <input className={styles.input} id="id" type="text" />
        </div> */}
        <div className={styles.input_content}>
          <label htmlFor="username">닉네임</label>
          <input
            className={styles.input}
            id="username"
            name="username"
            type="text"
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <button type="submit" onClick={handleSubmit}>
          회원가입
        </button>
      </form>
    </div>
  );
}
