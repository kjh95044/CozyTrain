import styles from "./PrimaryButton.module.css";

export default function PrimaryButton(props) {
  return (
    <button className={styles.button} onClick={props.onClick}>
      {props.children}
    </button>
  );
}
