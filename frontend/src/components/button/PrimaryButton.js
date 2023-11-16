import styles from "./PrimaryButton.module.css";

export default function PrimaryButton(props) {
  const buttonStyle = {
    height: props.height || "",
  };

  return (
    <button
      className={styles.button}
      onClick={props.onClick}
      style={buttonStyle}
    >
      {props.children}
    </button>
  );
}
