"use client";

import styles from "./page.module.css";
import NavBottom from "@/components/NavBottom";
import PrimaryButton from "@/components/button/PrimaryButton";
import Link from "next/link";

export default function Dream() {
  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <b>꿈</b>
      </div>
      <div className={styles.note_container}>
        <div className={styles.next}>
          <Link href={`/dream/new`}>
            <PrimaryButton>오늘의 꿈</PrimaryButton>
          </Link>
        </div>
      </div>
      <NavBottom />
    </div>
  );
}
