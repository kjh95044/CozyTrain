import BackGround from "@/components/Background";
import SleepGirl from "@/components/Lottie/SleepGirl";
import styles from "./loading.module.css";

export default function Loading() {
  const tips = [
    "잠자리에 드는 시간과 아침에 일어 나는 시간을 일정하게, 규칙적으로 하십시오.",
    "낮에 40분 동안 땀이 날 정도의 운동은 수면에 도움이 됩니다.",
    "낮잠은 가급적 안 자도록 노력하시고, 자더라도 15분 이내로 제한하도록 하십시오.",
    "잠자기 4-6시간 전에는 카페인(커피, 콜라, 녹차, 홍차 등)이 들어 있는 음식을 먹지 않도록 하시고, 하루 중에도 카페인의 섭취를 최소화 하는 것이 좋습니다.",
    "담배를 피우신다면 끊는 것이 좋은 수면에 도움이 됩니다.",
    "잠자리에 소음을 없애고, 온도와 조명을 안락하게 조절하도록 하십시오.",
    "수면제는 매일, 습관적으로 사용하지 않는 것이 좋습니다.",
    "과도한 스트레스와 긴장을 피하고 이완하는 것을 배우면 수면에 도움이 됩니다.",
    "잠자리에 들어 20분 이내 잠이 오지 않는다면, 잠자리에서 일어나 가벼운 독서, TV 시청 등을 하면서 이완하고 있다가 다시 졸리면 다시 잠자리에 들도록 하십시오. 이후 다시 잠이 안 오면 이러한 과정을 잠들 때까지 계속 반복하십시오.",
    "과도한 스트레스와 긴장을 피하고 이완하는 것을 배우면 수면에 도움이 됩니다.",
    "잠자리에 들어 20분 이내 잠이 오지 않는다면, 잠자리에서 일어나 가벼운 독서, TV 시청 등을 하면서 이완하고 있다가 다시 졸리면 다시 잠자리에 들도록 하십시오.",
  ];

  return (
    <>
      <div className={styles.container}>
        <div className={styles.content}>
          <SleepGirl />
          <div className={styles.text}>
            <p>- TIP -</p>
            {tips[Math.floor(Math.random() * tips.length)]}
          </div>
        </div>
      </div>
      <BackGround />
    </>
  );
}
