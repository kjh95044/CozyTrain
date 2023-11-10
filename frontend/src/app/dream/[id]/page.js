"use client";

import Header from "@/components/Header";
import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import BackButton from "@/components/button/BackButton";
import CancelButton from "@/components/button/CancelButton";
import getFetch from "@/services/getFetch";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function Detail() {
  let c = useParams();
  const [dream, setDream] = useState();

  const getDream = async () => {
    const data = await getFetch(`dream/${c.id}`);
    setDream(data.response);
  };

  useEffect(() => {
    getDream();
  }, [c]);

  return (
    <div className={styles.container}>
      <Header>꿈</Header>

      {dream ? (
        <div className={styles.note_container}>
          <div className={styles.title}>{dream.dreamDate}</div>
          <textarea
            name="content"
            className={styles.content}
            value={dream.dreamContent}
            readOnly
          />
          <div className={styles.write_btn}>
            <div>
              <BackButton>뒤로가기</BackButton>
            </div>
          </div>
        </div>
      ) : (
        ""
      )}

      <NavBottom />
    </div>
  );
}
