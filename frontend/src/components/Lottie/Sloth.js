"use client";
import { useLottie } from "lottie-react";

import sloth from "/public/lotties/sloth.json";

export default function Sloth() {
  const { View } = useLottie({
    animationData: sloth,
  });

  return <div style={{ width: "80%" }}>{View}</div>;
}
