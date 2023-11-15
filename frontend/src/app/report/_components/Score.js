import Image from "next/image";
import { useState, useEffect } from "react";

import train from "#/icons/train64.png";
import styles from "./Score.module.css";
import Walking from "@/components/Lottie/Walking";
import Coffee from "@/components/Lottie/Coffee";
import Meditation from "@/components/Lottie/Meditation";
import Sloth from "@/components/Lottie/Sloth";

export default function Score(props) {
  const [caffeineDiff, setCaffeineDiff] = useState(0);
  const [stepsDiff, setStepsDiff] = useState(0);
  const [stressLevelDiff, setStressLeveleDiff] = useState(0);
  const [scoreDiff, setScoreDiff] = useState(0);
  const [keyFactorIdx, setKeyFactorIdx] = useState(0);
  const [keyFactor, setKeyFactor] = useState("");
  const [moveDist, setMoveDist] = useState(50);

  useEffect(() => {
    const caffeineDiff = props.report.caffeine - props.averageReport.caffeine;
    const stepsDiff = props.report.steps - props.averageReport.steps;
    const stressLevelDiff =
      props.report.stressLevel - props.averageReport.stressLevel;
    const scoreDiff = props.report.sleepScore - props.averageReport.sleepScore;

    setCaffeineDiff(caffeineDiff);
    setStepsDiff(stepsDiff);
    setStressLeveleDiff(stressLevelDiff);
    setScoreDiff(scoreDiff);

    setMoveDist((props.report.moveDist / 300) * 100);
    if (caffeineDiff === 0 && stepsDiff === 0 && stressLevelDiff === 0) {
      setKeyFactor("수면 시간");
      setKeyFactorIdx(3);
    } else {
      let max = 0;
      if (caffeineDiff > max) {
        max = Math.max(caffeineDiff, max);
        setKeyFactor("카페인 섭취");
        setKeyFactorIdx(0);
      }
      if (stepsDiff < 0 && Math.abs(stepsDiff) / 20 > max) {
        max = Math.max(Math.abs(stepsDiff) / 20, max);
        setKeyFactor("걸음 수");
        setKeyFactorIdx(1);
      }
      if (stressLevelDiff > max) {
        max = Math.max(stressLevelDiff, max);
        setKeyFactor("스트레스");
        setKeyFactorIdx(2);
      }

      let min = 100;
      if (max === 0) {
        if (Math.abs(caffeineDiff) < min) {
          min = Math.min(Math.abs(caffeineDiff), min);
          setKeyFactor("카페인 섭취");
          setKeyFactorIdx(0);
        }
        if (stepsDiff > 0) {
          min = Math.min(stepsDiff / 20, min);
          setKeyFactor("걸음 수");
          setKeyFactorIdx(1);
        }
        if (Math.abs(stressLevelDiff) < min) {
          min = Math.min(Math.abs(stressLevelDiff), min);
          setKeyFactor("스트레스");
          setKeyFactorIdx(2);
        }
      }
    }
  }, [props]);

  const formatScore = (score) => {
    if (score > 0) {
      return `+${score}`;
    }

    return score;
  };

  return (
    <div className={styles.layout}>
      <div className={styles.calc}>
        <div className={styles.score}>
          점수: {props.report.sleepScore}점({scoreDiff})
        </div>
        <div className={styles.moveDist}>
          이동 거리: {props.report.moveDist}km
        </div>
      </div>

      <div className={styles.health}>
        <div>
          카페인 섭취량: {props.report.caffeine}mg({formatScore(caffeineDiff)})
        </div>
        <div>
          걸음 수: {props.report.steps}걸음(
          {formatScore(stepsDiff)})
        </div>
        <div>
          스트레스 지수: {props.report.stressLevel}%(
          {formatScore(stressLevelDiff)})
        </div>
      </div>

      <div className={styles.content}>
        <p className={styles.p}>
          다른 날과 비교하여 {keyFactor}에 가장 큰 변화가 있었습니다.
        </p>
        {keyFactor === "카페인 섭취" && (
          <p className={styles.p}>
            잠자기 6시간 전에는 카페인 섭취를 최소화 하는것이 좋아요:)
          </p>
        )}
        {keyFactor === "걸음 수" && (
          <p className={styles.p}>
            낮 시간의 야외 활동은 수면에 도움이 됩니다:)
          </p>
        )}
        {keyFactor === "스트레스" && (
          <p className={styles.p}>
            스트레스를 낮추기 위해 잠시 휴식을 가져보는게 어떨까요?
          </p>
        )}
        {keyFactor === "수면 시간" && <p>규칙적인 수면이 중요해요 :O</p>}
      </div>
    </div>
  );
}
