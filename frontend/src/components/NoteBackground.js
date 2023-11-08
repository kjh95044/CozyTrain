import Image from "next/image";
import note from "/public/images/note.png";

import cloud from "/public/images/cloud.png";
import styles from "./NoteBackground.module.css";

export default function NoteBackground() {
  return (
    <div className={styles.container}>
      <Image className={styles.note} src={note} alt="note"></Image>
      <Image className={styles.cloud} src={cloud} alt="구름"></Image>
    </div>
  );
}
