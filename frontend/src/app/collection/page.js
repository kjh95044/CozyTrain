"use client";

import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import Image from "next/image";
import Header from "@/components/Header";
import gacha from "/public/images/gacha.png";
import { useState } from "react";
import Modal from "@/components/Modal";

export default function Collection() {
  const [modal, setModal] = useState(false);

  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };

  return (
    <div className={styles.container}>
      {modal ? (
        <div>
          <Modal onClick={closeModal}>일단 모달</Modal>
        </div>
      ) : (
        <div>
          <Header>
            <div>컬렉션</div>
            <div className={styles.button} onClick={openModal}>
              <Image src={gacha} alt="버튼"></Image>
              <span>뽑기권</span>
            </div>
          </Header>
          <div className={styles.note_container}></div>
        </div>
      )}

      <NavBottom />
    </div>
  );
}
