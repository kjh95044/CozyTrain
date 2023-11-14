"use client";

import { useLottie } from "lottie-react";

import TurnSheepLottie from "/public/lotties/turn-sheep.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: TurnSheepLottie,
  });

  return (
    <div
      style={{
        height: "52%",
        width: "100%",
        position: "fixed",
        inset: "50%",
        transform: "translate(-50%, -48%)",
      }}
    >
      {View}
    </div>
  );
}
