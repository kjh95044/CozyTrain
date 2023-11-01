import styles from './MapModal.module.css'
import ConfirmButton from '../ConfirmButton'
import CancelButton from '../CancelButton'

import Link from 'next/link'

export default function MapModal(props) {

    return (
        <div className={styles.container}>
            <div className={styles.map_modal_text}>{props.text}</div>
            <div className={styles.map_modal_btn}>
                <Link href={`/map/${props.moveContinent}`}>
                    <div className={styles.map_modal_confirm_btn}>
                        <ConfirmButton children="확인" />
                    </div>
                </Link>
                <div className={styles.map_modal_cancel_btn}>
                    <CancelButton children="취소" onClick={props.onCloseModal} />
                </div>
            </div>


        </div>
    )
}