import Image from "next/image";

import styles from "./page.module.css";
import TurnSheep from "@/components/TurnSheep";
import KakaoLogin from "/public/images/kakao_login_medium_wide.png";

export default function Login() {
  return (
    <div>
      <div className={styles.title}>
        <div>칙칙</div>
        <div>포근포근</div>
      </div>
      <TurnSheep />
      <Image
        className={styles.kakaoLoginBtn}
        src={KakaoLogin}
        alt="카카오 로그인 버튼"
      ></Image>
    </div>
  );
}
