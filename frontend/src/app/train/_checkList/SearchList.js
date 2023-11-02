import styles from "./SearchList.module.css";

export default function SearchList() {
  return (
    <>
      <div className={styles.searchBar}>
        <input className={styles.input} type="text" />
        <button className={styles.btn}>검색</button>
      </div>
      <div className={styles.items}></div>
    </>
  );
}
