import styles from "./SecondaryButton.module.css";

export default function SecondaryButton(props) {
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
