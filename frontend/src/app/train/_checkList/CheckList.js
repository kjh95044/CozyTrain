import { useState, useEffect } from "react";

import useStore from "@/store/useStore";
import CloseButton from "@/components/button/CloseButton";
import SearchPage from "./_search/page";
import TodayPage from "./_today/page";
import styles from "./CheckList.module.css";

export default function CheckList(props) {
  const [serachData, setSearchData] = useState([]);
  const [isList, setIsList] = useState(false);
  const { memberName } = useStore();

  const handleListTrue = () => setIsList(true);
  const handleListFalse = () => setIsList(false);

  useEffect(() => {
    console.log(memberName);
  }, []);

  return (
    <div>
      <div className={styles.closeBtn}>
        <CloseButton onClick={props.onClick} />
      </div>

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
          추가
        </button>
      </div>
    </div>
  );
}
