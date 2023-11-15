import Image from "next/image"
import Train from "public/images/map/train.png"
import Link from "next/link"

import styles from "./TrainButton.module.css"

export default function TrainButton() {

    return (
        <button className={styles.container}>
            <Image src={Train} alt="기차 이동" width={45} height={45} />
        </button>
    )
}