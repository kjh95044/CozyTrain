"use client";
import { useLottie } from "lottie-react";

import gift from "/public/lotties/gift.json";

export default function Gift() {
  const { View } = useLottie({
    animationData: gift,
  });

  return <div style={{ width: "100%" }}>{View}</div>;
}
