import styles from "./SecondaryButton.module.css";

export default function SecondaryButton(props) {
  return (
    <button className={styles.button} onClick={props.onClick}>
      {props.children}
    </button>
  );
}
