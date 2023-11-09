"use client";

import { useState, useEffect } from "react";
import Image from "next/image";

import useStore from "@/store/useStore";
import getFetch from "@/services/getFetch";
import china from "#/images/city/china.jpg";
import jeju from "#/images/city/jeju.png";
import seoul from "#/images/city/seoul.jpg";
import tokyo from "#/images/city/tokyo.jpg";
import sapporo from "#/images/city/sapporo.png";
import busan from "#/images/city/busan.jpg";
import osaka from "#/images/city/osaka.png";
import styles from "./page.module.css";

export default function Window() {
  const [region, setRegion] = useState("");
  const { setLocation } = useStore();

  const getCurLocation = async () => {
    const resp = await getFetch("train/cur-location-info");
    const location = resp.response;

    setLocation(location.region, location.dist);

    switch (location.region) {
      case "seoul":
        setRegion(seoul);
        break;
      case "busan":
        setRegion(busan);
        break;
      case "jeju":
        setRegion(jeju);
        break;
      case "sapporo":
        setRegion(sapporo);
        break;
      case "tokyo":
        setRegion(tokyo);
        break;
      case "osaka":
        setRegion(osaka);
        break;
      default:
        setRegion(china);
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
