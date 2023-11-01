"use client";

import { useEffect, useState } from "react";
import Image from "next/image";

import letter from "#/images/letter.png";
import styles from "./Letter.module.css";

export default function Letter() {
  const [showLetter, setShowLetter] = useState(false);
  const [shakeLetter, setShakeLetter] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setShowLetter(true);

      setTimeout(() => {
        setShakeLetter(true);
      }, 1600);
    }, 1000);

    return () => {};
  }, []);

  return (
    <Image
      className={`${styles.letter} ${showLetter ? styles.arive : ""} ${
        shakeLetter ? styles.shake : ""
      }`}
      src={letter}
      alt="편지"
    ></Image>
  );
}
