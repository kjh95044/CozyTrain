"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

import useStore from "@/store/useStore";
import postFetch from "@/services/postFetch";
import styles from "./LoginForm.module.css";

export default function LoginForm() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();
  const { login } = useStore();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      memberId: id,
      memberPassword: password,
    };

    try {
      const data = await postFetch("member/login", formData);
      const respData = data.response;

      console.log(respData);

      document.cookie = `accessToken=${respData.accessToken}`;
      document.cookie = `refreshToken=${respData.refreshToken}`;
      document.cookie = `todayFirstLogin=false`;

      login(
        respData.memberName,
        respData.memberProfileImg,
        respData.accessToken
      );

      router.push("/");
    } catch (e) {
      console.log(e);
    }
  };

  return (
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
      <div className={styles.button_container}>
        <button className={styles.button} type="submit" onClick={handleSubmit}>
          로그인
        </button>
      </div>
    </form>
  );
}
