"use client";
import { useLottie } from "lottie-react";

import walking from "/public/lotties/walking.json";

export default function Walking() {
  const { View } = useLottie({
    animationData: walking,
  });

  return <div style={{ width: "60%" }}>{View}</div>;
}
