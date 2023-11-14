import Image from "next/image";

import Window from "./_window/page";
import Navleft from "./NavLeft";
import inTrain from "#/images/intrain.jpg";
import styles from "./page.module.css";
import RepresentCollection from "./RepresentCollection";

export default function Page() {
  return (
    <>
      <Navleft />
      <Window />
      <Image className={styles.inTrain} src={inTrain} alt="기차 내부" />
      <RepresentCollection />
    </>
  );
}
