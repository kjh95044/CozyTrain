"use client";

import { useEffect, useState } from "react";
import Image from "next/image";
import Link from "next/link";

import useStore from "@/store/useStore";
import loginFetch from "@/services/auth/loginFetch";
import PrimaryButton from "@/components/button/PrimaryButton";
import Modal from "@/components/Modal";
import letter from "#/images/letter.png";
import styles from "./Letter.module.css";

export default function Letter() {
  const [showLetter, setShowLetter] = useState(false);
  const [shakeLetter, setShakeLetter] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const { login } = useStore();

  const getUserInfo = async () => {
    const cookies = document.cookie.split("; ");
    let memberId;
    let memberPassword;
    cookies.forEach((cookie) => {
      const [key, value] = cookie.split("=");
      if (key === "id") {
        memberId = value;
      }
      if (key === "pw") {
        memberPassword = value;
      }
    });

    const formData = {
      memberId,
      memberPassword,
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
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getUserInfo();

    setTimeout(() => {
      setShowLetter(true);

      setTimeout(() => {
        setShakeLetter(true);
      }, 1600);
    }, 1000);
  }, []);

  return (
    <>
      <Image
        className={`${styles.letter} ${showLetter ? styles.arive : ""} ${
          shakeLetter ? styles.shake : ""
        }`}
        src={letter}
        alt="편지"
        onClick={() => setShowModal(true)}
      />

      {showModal && (
        <Modal onClick={() => setShowModal(false)}>
          <div>수면 리포트 샬라샬라</div>

          <Link href={"/train"}>
            <PrimaryButton>확인</PrimaryButton>
          </Link>
        </Modal>
      )}
    </>
  );
}
