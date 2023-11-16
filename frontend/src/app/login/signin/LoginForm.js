"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

import useStore from "@/store/useStore";
import loginFetch from "@/services/auth/loginFetch";
import styles from "./LoginForm.module.css";

export default function LoginForm() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [success, setSuccess] = useState(true);
  const router = useRouter();
  const { login } = useStore();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      memberId: id,
      memberPassword: password,
    };

    try {
      const data = await loginFetch("member/login", formData);
      const respData = data.response;
      let date = new Date();
      date.setTime(date.getTime() + 14 * 24 * 60 * 60 * 1000);
      const expiresDate = date.toGMTString();

      document.cookie = `accessToken=${respData.accessToken}; path=/`;
      document.cookie = `id=${id}; expires=${expiresDate}; path=/`;
      document.cookie = `pw=${password}; expires=${expiresDate}; path=/`;

      login(respData.memberName, respData.memberProfileImg);

      router.push("/");
    } catch (e) {
      console.log(e);
      setSuccess(false);
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
      {!success && (
        <div style={{ color: "red" }}>
          <p style={{ textAlign: "center", marginBottom: "4px" }}>
            로그인에 실패했습니다.
          </p>
          <p style={{ textAlign: "center" }}>
            아이디와 비밀번호를 확인해주세요.
          </p>
        </div>
      )}
      <div className={styles.button_container}>
        <button className={styles.button} type="submit" onClick={handleSubmit}>
          로그인
        </button>
      </div>
    </form>
  );
}
