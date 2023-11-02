import styles from "./PrimaryButton.module.css";

export default function ConfirmButton(props) {
    return (
        <button className={styles.container}>
            {props.children}
        </button>
    );
}
