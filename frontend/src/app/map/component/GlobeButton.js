import Image from "next/image"
import Globe from "public/images/map/globe.png"
import Link from "next/link"

import styles from "./GlobeButton.module.css"

export default function GlobeButton() {

    return (
        <Link href={'/map/asia'}>
            <button className={styles.container}>
                <Image src={Globe} alt="지구본 이동" width={40} height={40} />
            </button>
        </Link>
    )
}