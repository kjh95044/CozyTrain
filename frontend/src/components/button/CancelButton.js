import styles from "./CancelButton.module.css";

export default function CancelButton(props) {
  const color = props.color ? "blue" : "red";

  return (
    <button onClick={props.onClick} className={styles.container}>
      {props.children}
    </button>
  );
}
