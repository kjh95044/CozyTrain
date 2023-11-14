import { useState } from "react";

import SearchPage from "./_search/page";
import TodayPage from "./_today/page";
import styles from "./CheckList.module.css";

export default function CheckList() {
  const [isList, setIsList] = useState(false);

  const handleListTrue = () => setIsList(true);
  const handleListFalse = () => setIsList(false);

  return (
    <div>
      <div className={styles.content}>
        {isList ? <TodayPage /> : <SearchPage />}
      </div>

      <div className={styles.list}></div>
      <div className={styles.btn_container}>
        <button
          className={isList ? styles.btn_click : styles.btn}
          onClick={handleListTrue}
        >
          목록
        </button>
        <button
          className={isList ? styles.btn : styles.btn_click}
          onClick={handleListFalse}
        >
          검색
        </button>
      </div>
    </div>
  );
}
