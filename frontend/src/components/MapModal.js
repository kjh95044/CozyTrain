import styles from './MapModal.module.css'

export default function MapModal(props) {

    const { text } = props.modalProps;

    return (
        <div className={styles.container}>
            <div className={styles.map_modal_text}>{text}</div>
        </div>
    )
}