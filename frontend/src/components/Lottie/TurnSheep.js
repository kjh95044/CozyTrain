"use client";

import { useLottie } from "lottie-react";

import TurnSheepLottie from "/public/lotties/turn-sheep.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: TurnSheepLottie,
  });

  return <>{View}</>;
}
