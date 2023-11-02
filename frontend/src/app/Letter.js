"use client";

import { useEffect, useState } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";

import CancelButton from "@/components/button/CancelButton";
import Modal from "@/components/Modal";
import letter from "#/images/letter.png";
import styles from "./Letter.module.css";

export default function Letter() {
  const [showLetter, setShowLetter] = useState(false);
  const [shakeLetter, setShakeLetter] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const router = useRouter();

  const handleRedirect = () => {
    router.push("/train");
  };

  useEffect(() => {
    setTimeout(() => {
      setShowLetter(true);

      setTimeout(() => {
        setShakeLetter(true);
      }, 1600);
    }, 1000);

    return () => { };
  }, []);

  return (
    <>
      <Image
        className={`${styles.letter} ${showLetter ? styles.arive : ""} ${shakeLetter ? styles.shake : ""
          }`}
        src={letter}
        alt="편지"
        onClick={() => setShowModal(true)}
      />

      {showModal && (
        <Modal>
          수면 리포트 샬라샬라
          <div className={styles.modal_btn}>
            <CancelButton onClick={handleRedirect}>확인</CancelButton>
          </div>
        </Modal>
      )}
    </>
  );
}
