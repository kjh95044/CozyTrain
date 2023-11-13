import styles from "./SearchResult.module.css";

export default function SearchResult(props) {
  return (
    <div className={styles.items}>
      {props.items.map((e, i) => (
        <div className={styles.item} key={i}>
          <div className={styles.radioBtn}></div>
          <div>{e.name}</div>
          <div>즐찾</div>
        </div>
      ))}
    </div>
  );
}
