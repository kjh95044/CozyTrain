"use client";
import { useLottie } from "lottie-react";

import earth from "/public/lotties/earth.json";

export default function Earth() {
  const { View } = useLottie({
    animationData: earth,
  });

  return <div style={{ width: "64%" }}>{View}</div>;
}
