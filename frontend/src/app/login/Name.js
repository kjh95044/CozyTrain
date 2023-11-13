import styles from "./Name.module.css";

export default function Name() {
  return (
    <div className={styles.body}>
      <h1 className={styles.h}>
        <span className={styles.sp}>칙</span>
        <span className={styles.sp}>칙</span>
        <span className={styles.sp}>포</span>
        <span className={styles.sp}>근</span>
        <span className={styles.sp}>포</span>
        <span className={styles.sp}>근</span>
      </h1>
    </div>
  );
}
