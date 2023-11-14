"use client";
import { useLottie } from "lottie-react";

import coffee from "/public/lotties/coffee.json";

export default function Coffee() {
  const { View } = useLottie({
    animationData: coffee,
  });

  return <div style={{ width: "60%" }}>{View}</div>;
}
