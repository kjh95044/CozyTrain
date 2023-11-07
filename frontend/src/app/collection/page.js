"use client";

import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import Image from "next/image";
import Header from "@/components/Header";
import gacha from "/public/images/gacha.png";
import { useState, useEffect } from "react";
import Modal from "@/components/Modal";
import getFetch from "@/services/getFetch";

export default function Collection() {
  const [modal, setModal] = useState(false);
  const [collection, setCollection] = useState([]);

  const countrys = ["한국", "일본", "태국", "중국"];
  const cnt = 8;

  useEffect(() => {
    getCollection();
  }, []);

  const getCollection = async () => {
    const data = await getFetch("collection");
    setCollection(data.response.items);
    console.log(data.response.items);
  };

  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };

  return (
    <div className={styles.container}>
      <Header>
        <div>컬렉션</div>
        <div className={styles.button} onClick={openModal}>
          <Image src={gacha} alt="버튼"></Image>
          <span>뽑기권</span>
        </div>
      </Header>
      {modal ? (
        <div>
          <Modal onClick={closeModal}>일단 모달</Modal>
        </div>
      ) : (
        <div className={styles.note_container}>
          <div className={styles.scroll_container}>
            {collection.length === 32 &&
              countrys.map((country, countryIdx) => (
                <div key={countryIdx}>
                  <h3>{country}</h3>
                  <div className={styles.item_container}>
                    {collection
                      .slice(countryIdx * cnt, (countryIdx + 1) * cnt)
                      .map((item, i) => (
                        //country, itemDescription, ItemImgUrl, itemName, own
                        <div
                          key={i}
                          className={`${styles.item_yes} ${
                            item.own ? "" : styles.item_no
                          }`}
                        >
                          <Image
                            src={item.itemImgUrl}
                            alt="스티커"
                            width={60}
                            height={60}
                          ></Image>
                        </div>
                      ))}
                  </div>
                </div>
              ))}
          </div>
        </div>
      )}

      <NavBottom />
    </div>
  );
}
