"use client";

import { useLottie } from "lottie-react";

import TurnSheepLottie from "/public/lotties/sheep_stop.json";

export default function TurnSheep() {
  const { View } = useLottie({
    animationData: TurnSheepLottie,
  });

  return <>{View}</>;
}
