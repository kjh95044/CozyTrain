import styles from "./SecondaryButton.module.css";

export default function SecondaryButton(props) {
  return <button className={styles.container}>{props.children}</button>;
}
