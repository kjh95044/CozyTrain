import styles from "./SecondaryButton.module.css";

export default function SecondaryButton(props) {
  return <button className={styles.button}>{props.children}</button>;
}
