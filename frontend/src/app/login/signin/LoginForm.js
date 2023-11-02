"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

import post from "@/services/use-post";
import useStore from "@/store/useStore";
import styles from "./LoginForm.module.css";

export default function LoginForm() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      memberId: id,
      memberPassword: password,
    };

    // fetch("https://dev.cozytrain.com/api/member/login", {
    //   method: "POST",
    //   headers: { "Content-Type": "application/json" },
    //   body: JSON.stringify(formData),
    // })
    //   .then((resp) => resp.json())
    //   .then((data) => {
    //     const respData = data.response;

    //     document.cookie = `accessToken=${data.response.accessToken}`;
    //     document.cookie = `refreshToken=${data.response.refreshToken}`;
    //     document.cookie = `todayFirstLogin=false`;

    //     login(
    //       respData.memberName,
    //       respData.memberProfileImg,
    //       respData.accessToken
    //     );

    //     router.push("/");
    //   })
    //   .catch((error) => console.log(error));

    try {
      const respData = await post("member/login", formData);

      // router.push("/");
    } catch (e) {
      console.log("에러요" + e);
    }

    // router.push("/");
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
