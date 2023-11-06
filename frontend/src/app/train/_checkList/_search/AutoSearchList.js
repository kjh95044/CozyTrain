import Search from "@/components/icons/Search";
import styles from "./AutoSearchList.module.css";

export default function AutoSearchList(props) {
  return (
    <div className={styles.list}>
      {props.autoSearchValue.map((e, idx) => {
        return (
          <div className={styles.item} key={idx}>
            <div className={styles.icon}>
              <Search />
            </div>
            <div className={styles.content}>{e}</div>
          </div>
        );
      })}
    </div>
  );
}
