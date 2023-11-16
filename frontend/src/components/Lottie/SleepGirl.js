"use client";
import { useLottie } from "lottie-react";

import sleepGirl from "/public/lotties/sleep-girl.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: sleepGirl,
  });

  return <div style={{ width: "80%" }}>{View}</div>;
}
