"use client";

import React, { useEffect, useState } from "react";
import Image from "next/image";
import AriveTrain from "#/lotties/arive-train.gif";

export default function TurnSheep() {
  const displayTime = 3400;

  useEffect(() => {
    const timer = setTimeout(() => {}, displayTime);

    return () => {
      clearTimeout(timer);
    };
  }, []);

  return (
    <div style={{ width: "100vw", height: "100vh", position: "relative" }}>
      <Image src={AriveTrain} alt="기차 도착" fill />
    </div>
  );
}
