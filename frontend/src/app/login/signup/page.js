"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import getFetch from "@/services/auth/getFetch";
import postFetch from "@/services/auth/postFetch";
import Title from "../Title";
import styles from "./page.module.css";

export default function SingUp() {
  const [id, setId] = useState("");
  const [isDuplicateId, setIsDuplicateId] = useState(0);
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
  const [username, setUsername] = useState("");
  const [emptyInput, setEmptyInput] = useState(false);
  const router = useRouter();

  const CheckDuplicateId = async (e) => {
    e.preventDefault();
    const resp = await getFetch(`member/${id}`);

    if (resp.response) setIsDuplicateId(1);
    else setIsDuplicateId(2);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      !id ||
      !password ||
      !passwordCheck ||
      !username ||
      password !== passwordCheck
    ) {
      setEmptyInput(true);
      return;
    }

    const formData = {
      memberId: id,
      memberPassword: password,
      memberName: username,
    };

    postFetch("member/signup", formData)
      .then(() => {
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
        {isDuplicateId == "1" ? (
          <div className={styles.textRed}>이미 존재하는 아이디입니다.</div>
        ) : isDuplicateId == "2" ? (
          <div className={styles.possibleId}>사용 가능한 아이디입니다.</div>
        ) : (
          ""
        )}
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

        {password !== passwordCheck ? (
          <div className={styles.textRed}>비밀번호가 일치하지 않습니다.</div>
        ) : (
          ""
        )}

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

        <div className={styles.textRed}>
          {emptyInput && !id && <p>아이디를 입력해주세요.</p>}
          {emptyInput && !password && <p>비밀번호를 입력해주세요.</p>}
          {emptyInput && !username && <p>닉네임을 입력해주세요.</p>}
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
