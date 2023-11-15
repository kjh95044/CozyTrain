"use client";

import { useState, useEffect } from "react";
import Image from "next/image";

import useStore from "@/store/useStore";
import getFetch from "@/services/getFetch";
import china from "#/images/city/china.jpg";
import jeju from "#/images/city/jeju.png";
import seoul from "#/images/city/seoul.jpg";
import tokyo from "#/images/city/tokyo.jpg";
import sapporo from "#/images/city/sapporo.jpg";
import busan from "#/images/city/busan.jpg";
import osaka from "#/images/city/osaka.png";
import styles from "./page.module.css";

export default function Window() {
  const [region, setRegion] = useState("");
  const { setLocation } = useStore();

  const getCurLocation = async () => {
    const resp = await getFetch("train/cur-location-info");
    const location = resp.response;

    switch (location.region) {
      case "seoul":
        setRegion(seoul);
        setLocation("한국 서울", location.dist);
        break;
      case "busan":
        setRegion(busan);
        setLocation("한국 부산", location.dist);
        break;
      case "jeju":
        setRegion(jeju);
        setLocation("한국 제주도", location.dist);
        break;
      case "sapporo":
        setRegion(sapporo);
        setLocation("일본 삿포로", location.dist);
        break;
      case "tokyo":
        setRegion(tokyo);
        setLocation("일본 도쿄", location.dist);
        break;
      case "osaka":
        setRegion(osaka);
        setLocation("일본 오사카", location.dist);
        break;
      default:
        setRegion(china);
        setLocation("중국..", location.dist);
        break;
    }
  };

  useEffect(() => {
    getCurLocation();
  }, []);

  return region ? (
    <Image src={region} alt="" className={styles.city}></Image>
  ) : null;
}
