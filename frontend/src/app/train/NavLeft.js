"use client";

import { useState } from "react";
import Link from "next/link";
import Image from "next/image";

import CheckList from "./CheckList";
import Modal from "@/components/Modal";
import setting from "#/icons/setting.png";
import map from "#/icons/map.png";
import checkList from "#/icons/check-list.png";
import styles from "./NavLeft.module.css";

export default function NavLeft() {
  const [checkListModal, setCheckListModal] = useState(true);

  const handleCheckListClose = () => {
    setCheckListModal(false);
  };

  return (
    <div className={styles.nav}>
      <Image className={styles.nav_item} src={setting} alt="설정"></Image>
      <Link href="/map">
        <Image className={styles.nav_item} src={map} alt="지도"></Image>
      </Link>
      <div onClick={() => setCheckListModal(true)}>
        <Image
          className={styles.nav_item}
          src={checkList}
          alt="체크 리스트"
        ></Image>
      </div>

      {checkListModal && (
        <Modal>
          <CheckList onClose={handleCheckListClose} />
        </Modal>
      )}
    </div>
  );
}
