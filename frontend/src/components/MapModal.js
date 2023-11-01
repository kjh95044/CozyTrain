import styles from './MapModal.module.css'
import ConfirmButton from './ConfirmButton'
import CancelButton from './CancelButton'

export default function MapModal(props) {

    return (
        <div className={styles.container}>
            <div className={styles.map_modal_text}>{props.text}</div>
            <div className={styles.map_modal_btn}>
                <div className={styles.map_modal_confirm_btn}>
                    <ConfirmButton children="확인" />
                </div>
                <div className={styles.map_modal_cancel_btn}>
                    <CancelButton children="취소" onClick={props.onCloseModal} />
                </div>
            </div>


        </div>
    )
}