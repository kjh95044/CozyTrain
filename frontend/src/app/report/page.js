import Daily from "./Daily";
import styles from "./page.module.css";
import BackGround from "@/components/Background";
import NavBottom from "@/components/NavBottom";
import Header from "@/components/Header";

export default function Report() {
  return (
    <div className={styles.layout}>
      <BackGround></BackGround>
      <Header>리포트</Header>

      <Daily></Daily>

      <NavBottom />
    </div>
  );
}
