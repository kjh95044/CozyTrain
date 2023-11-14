import styles from './MapModal.module.css'
import ConfirmButton from '../../../components/button/PrimaryButton'
import CancelButton from '../../../components/button/CancelButton'

import Link from 'next/link'

export default function MapModal(props) {

    return (
        <div className={styles.container}>
            <div className={styles.map_modal_text}>{props.text}</div>
            <div className={styles.map_modal_btn}>
                <Link href={`/map/${props.move}`}>
                    <div className={styles.map_modal_confirm_btn}>
                        <ConfirmButton>확인</ConfirmButton>
                    </div>
                </Link>
                <div className={styles.map_modal_cancel_btn}>
                    <CancelButton onClick={props.onCloseModal}>취소</CancelButton>
                </div>
            </div>


        </div>
    )
}