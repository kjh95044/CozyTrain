import styles from "./PrimaryButton.module.css";

export default function PrimaryButton(props) {
  return <button className={styles.button}>{props.children}</button>;
}
