"use client";

import { useState } from "react";
import Link from "next/link";
import Image from "next/image";

import Setting from "./_setting/page";
import CheckList from "./_checkList/CheckList";
import Modal from "@/components/Modal";
import setting from "#/icons/setting.png";
import map from "#/icons/map.png";
import checkList from "#/icons/check-list.png";
import styles from "./NavLeft.module.css";

export default function NavLeft() {
  const [settingModal, setSettingModal] = useState(false);
  const [checkListModal, setCheckListModal] = useState(false);

  const handleModalClose = () => {
    setSettingModal(false);
    setCheckListModal(false);
  };

  const handleSettingClose = () => {
    setSettingModal(false);
  };

  const handleCheckListClose = () => {
    setCheckListModal(false);
  };

  return (
    <div className={styles.nav}>
      <div onClick={() => setSettingModal(true)}>
        <Image className={styles.nav_item} src={setting} alt="설정"></Image>
      </div>
      <div>
        <Link href="/map">
          <Image className={styles.nav_item} src={map} alt="지도"></Image>
        </Link>
      </div>
      <div onClick={() => setCheckListModal(true)}>
        <Image
          className={styles.nav_item}
          src={checkList}
          alt="체크 리스트"
        ></Image>
      </div>

      {settingModal && (
        <Modal onClick={handleModalClose}>
          <Setting onClick={handleSettingClose} />
        </Modal>
      )}

      {checkListModal && (
        <Modal onClick={handleModalClose}>
          <CheckList onClick={handleCheckListClose} />
        </Modal>
      )}
    </div>
  );
}
