import Image from "next/image";
import Link from "next/link";
import CloseMap from "public/images/map/close-map.png"

import styles from "./MapCloseButton.module.css"

export default function MapCloseButton() {
    return (
        <Link href="/home">
            <button className={styles.container}>
                <Image src={CloseMap} alt="지도 닫기" />
            </button>
        </Link>
    );
}
