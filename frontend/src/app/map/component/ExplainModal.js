import styles from "./ExplainModal.module.css"
import loadingTrain from "/public/lotties/loading-train.json";
import BackGround from "@/components/Background";

import { useLottie } from "lottie-react";

const Overlay = (props) => {

    const {View} = useLottie({
        animationData: loadingTrain,
    })

  return (
    <div className={styles.overlay}>
        <BackGround></BackGround>
        <div>
            {View}
        </div>
        <div className={styles.text}>
            건물과 국기를 클릭해보세요! <br/>
            설명을 볼 수 있어요 ( •̀ ω •́ )✧
        </div>
    </div>
  );
};

export default function Modal(props) {

  return (
    <>
      <Overlay onClick={props.onClick} title={props.title}>
        {props.children}
      </Overlay>
    </>
  );
}
