import { useState } from "react";

import styles from "./SearchList.module.css";

export default function SearchList() {
  const [value, setValue] = useState("");

  const searchDrink = () => {
    console.log(value);
  };

  return (
    <>
      <div className={styles.searchBar}>
        <input
          className={styles.input}
          type="text"
          onChange={(e) => setValue(e.target.value)}
        />
        <button className={styles.btn} onClick={searchDrink}>
          검색
        </button>
      </div>
      <div className={styles.items}></div>
    </>
  );
}
