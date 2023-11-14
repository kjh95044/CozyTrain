'use client'

import styles from "./Marker.module.css"

export default function Marker(props) {

    const markerClassName = props.visited ? styles.markerVisited : styles.markerNotVisited

    const handleClick = () => {
        if (props.visited) {
            props.openModal(props.country);
        }
    }

    return (
        <button className={markerClassName}
            style={{
                left: props.position.x,
                top: props.position.y
            }}
            onClick={handleClick}>
        </button>
    )
}