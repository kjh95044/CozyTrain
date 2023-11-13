"use client";
import { useLottie } from "lottie-react";

import sleep404 from "/public/lotties/sleep404.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: sleep404,
  });

  return <div style={{ width: "64%" }}>{View}</div>;
}
