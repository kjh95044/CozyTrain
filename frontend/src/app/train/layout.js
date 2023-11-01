import NavBottom from "@/components/NavBottom";

import styles from "./layout.module.css";

export default function Layout({ children }) {
  return (
    <div className={styles.layout}>
      {children}
      <NavBottom />
    </div>
  );
}
