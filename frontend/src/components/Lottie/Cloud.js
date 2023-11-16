"use client";
import { useLottie } from "lottie-react";

import cloud from "/public/lotties/cloud.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: cloud,
  });

  return <div style={{ width: "79.4%" }}>{View}</div>;
}
