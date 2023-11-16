"use client";

import { useState, useEffect } from "react";
import Image from "next/image";

import useStore from "@/store/useStore";
import china from "#/images/city/china.jpg";
import jeju from "#/images/city/jeju.png";
import seoul from "#/images/city/seoul.jpg";
import tokyo from "#/images/city/tokyo.jpg";
import sapporo from "#/images/city/sapporo.jpg";
import busan from "#/images/city/busan.jpg";
import osaka from "#/images/city/osaka.png";
import styles from "./page.module.css";

export default function Window() {
  const [location, setLocation] = useState("");
  const { region } = useStore();

  const getCurLocation = () => {
    switch (region) {
      case "한국 서울":
        setLocation(seoul);
        break;
      case "한국 부산":
        setLocation(busan);
        break;
      case "한국 제주도":
        setLocation(jeju);
        break;
      case "일본 삿포로":
        setLocation(sapporo);
        break;
      case "일본 도쿄":
        setLocation(tokyo);
        break;
      case "일본 오사카":
        setLocation(osaka);
        break;
      default:
        setLocation(china);
        break;
    }
  };

  useEffect(() => {
    getCurLocation();
  }, []);

  return location ? (
    <Image src={location} alt="" className={styles.city}></Image>
  ) : null;
}
