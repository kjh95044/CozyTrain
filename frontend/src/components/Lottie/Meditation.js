"use client";
import { useLottie } from "lottie-react";

import meditation from "/public/lotties/meditation.json";

export default function Meditation() {
  const { View } = useLottie({
    animationData: meditation,
  });

  return <div style={{ width: "60%" }}>{View}</div>;
}
