import React, { useEffect } from "react";
import { useLottie } from "lottie-react";
import ProgressLottie from "/public/lotties/progress.json";

const Progress = ({ isPlaying }) => {
  const { View, play, pause } = useLottie({
    animationData: ProgressLottie,
    loop: true,
    autoplay: false,
  });

  useEffect(() => {
    if (isPlaying) {
      play();
    } else {
      pause();
    }
  }, [isPlaying, play, pause]);

  return <>{View}</>;
};

export default Progress;
