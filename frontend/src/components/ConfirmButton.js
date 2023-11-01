import styles from "./ConfirmButton.module.css";

export default function ConfirmButton(props) {
    return (
        <button onClick={props.onClick} className={styles.container}>
            {props.children}
        </button>
    );
}
