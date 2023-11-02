import Link from "next/link";

import styles from "./MapAllButton.module.css"

export default function MapAllButton() {
    return (
        <Link href="/map">
            <button className={styles.container}>
                전체 보기
            </button>
        </Link>
    );
}
