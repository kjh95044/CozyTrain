"use client";
import Image from "next/image";
import { useState, useEffect } from "react";

import styles from "./RepresentCollection.module.css";
import getFetch from "@/services/getFetch";

export default function RepresentCollection() {
  const [collection, setCollection] = useState({});

  useEffect(() => {
    getRepresentCollection();
  }, []);

  const getRepresentCollection = async () => {
    const resp = await getFetch("collection/representative");

    setCollection(resp.response);
  };

  return (
    <div className={styles.collection}>
      {Object.keys(collection).length ? (
        <Image
          src={collection.itemImgUrl}
          alt={collection.itemName}
          width={100}
          height={100}
        ></Image>
      ) : null}
    </div>
  );
}
