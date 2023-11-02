import styles from "./ConfirmButton.module.css";

export default function ConfirmButton(props) {
    return (
        <button className={styles.container}>
            {props.children}
        </button>
    );
}
