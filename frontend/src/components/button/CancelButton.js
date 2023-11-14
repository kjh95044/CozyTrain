import styles from "./CancelButton.module.css";

export default function CancelButton(props) {
  return (
    <button onClick={props.onClick} className={styles.container}>
      {props.children}
    </button>
  );
}
