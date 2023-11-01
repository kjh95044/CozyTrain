import Link from "next/link";
import Image from "next/image";

import setting from "public/icons/setting.png";
import map from "public/icons/map.png";
import checkList from "public/icons/check-list.png";
import styles from "./NavLeft.module.css";

export default function NavLeft() {
  return (
    <div className={styles.nav}>
      <Link href={"/setting"}>
        <Image className={styles.nav_item} src={setting} alt="설정"></Image>
      </Link>
      <div>
        <Image className={styles.nav_item} src={map} alt="지도"></Image>
      </div>
      <div>
        <Image
          className={styles.nav_item}
          src={checkList}
          alt="체크 리스트"
        ></Image>
      </div>
    </div>
  );
}
