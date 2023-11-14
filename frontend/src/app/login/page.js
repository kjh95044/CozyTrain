import Link from "next/link";

import Name from "./Name";
import Button from "@/components/button/Button";
import TurnSheep from "@/components/Lottie/TurnSheep";
import styles from "./page.module.css";

export default function Login() {
  return (
    <div className={styles.container}>
      <Name />

      <div className={styles.logo}>
        <TurnSheep />
      </div>

      <div className={styles.signup_button}>
        <Link href="login/signup">
          <Button>회원가입</Button>
        </Link>
      </div>
      <div className={styles.signin_button}>
        <Link href="login/signin">
          <Button>로그인</Button>
        </Link>
      </div>
    </div>
  );
}
