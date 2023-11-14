"use client";

import { useLottie } from "lottie-react";

import check from "/public/lotties/check.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: check,
  });

  return <div style={{ width: "240px" }}>{View}</div>;
}
