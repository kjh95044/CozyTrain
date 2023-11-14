import Background from "@/components/Background";

import styles from "./layout.module.css";

export default function Layout({ children }) {
  return (
    <div className={styles.container}>
      <Background />
      {children}
    </div>
  );
}
