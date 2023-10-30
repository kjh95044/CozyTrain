"use client";

import styles from "./Button.module.css";

export default function Button(props) {
  return (
    <div onClick={props.onClick} className={styles.container}>
      {props.children}
    </div>
  );
}
