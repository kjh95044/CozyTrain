import Image from "next/image";
import MapAll from "public/assets/image/map-all.png";
import styles from "./page.module.css"

export default function map() {
    return (
        <div className={styles.container}>
            <Image className={styles.map_all} src={MapAll} alt="지도 전체 화면"></Image>
        </div>
    )
}
