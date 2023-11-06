import Image from "next/image";
import styles from "./Header.module.css";

export default function Header(props) {
  return (
    <div className={styles.container}>
      <div className={styles.title}>{props.children}</div>
    </div>
  );
}
