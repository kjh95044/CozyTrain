import Link from "next/link";

import Button from "@/components/Button";
import TurnSheep from "@/components/TurnSheep";
import styles from "./page.module.css";

export default function Login() {
  return (
    <div className={styles.container}>
      <div className={styles.title}>
        <div>칙칙</div>
        <div>포근포근</div>
      </div>

      <div className={styles.logo}>
        <TurnSheep />
      </div>

      <div className={styles.button_container}>
        <Link href="login/signup">
          <Button>회원가입</Button>
        </Link>
        <Link href="login/signin">
          <Button>로그인</Button>
        </Link>
      </div>
    </div>
  );
}
