import styles from "./CheckList.module.css";

export default function CheckList() {
  return (
    <div>
      <div>오늘은 무엇을 마셨나요?</div>
      <div>
        <input></input>
        <button>검색</button>
      </div>
      <div className={styles.list}></div>
      <div></div>
    </div>
  );
}
