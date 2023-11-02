import Image from "next/image";

import closeButton from "#/icons/close-button.png";
import styles from "./CloseButton.module.css";

export default function CloseButton({ onClick }) {
  return (
    <div className={styles.btnContainer} onClick={onClick}>
      <Image className={styles.btn} src={closeButton} alt="닫기 버튼"></Image>
    </div>
  );
}
