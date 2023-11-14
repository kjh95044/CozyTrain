import Link from "next/link";

import styles from "./MapAllButton.module.css"

export default function MapAllButton(props) {
    return (
        <Link href="/map">
            <button className={styles.container}>
                {props.children}
            </button>
        </Link>
    );
}
