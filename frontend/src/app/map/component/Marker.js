import styles from "./Marker.module.css"

export default function Marker(props) {

    const markerClassName = props.visited ? styles.markerVisited : styles.markerNotVisited

    return (
        <button className={markerClassName}
                style={{
                    left: props.position.x,
                    top: props.position.y}}>
        </button>
    )
}