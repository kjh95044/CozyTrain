import Image from "next/image";

import Navleft from "./NavLeft";
import inTrain from "#/images/intrain.jpg";
import styles from "./page.module.css";

export default function Page() {
  return (
    <>
      <Navleft />
      <Image className={styles.inTrain} src={inTrain} alt="기차 내부" />
    </>
  );
}
