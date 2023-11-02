"use client";

import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";

import home from "public/icons/home.png";
import homeSelect from "public/icons/home-select.png";
import report from "public/icons/report.png";
import reportSelect from "public/icons/report-select.png";
import dream from "public/icons/dream.png";
import dreamSelect from "public/icons/dream-select.png";
import friend from "public/icons/friend.png";
import friendSelect from "public/icons/friend-select.png";
import collection from "public/icons/collection.png";
import collectionSelect from "public/icons/collection-select.png";
import styles from "./NavBottom.module.css";

export default function NavBottom() {
  const pathName = usePathname();

  return (
    <ul className={styles.nav}>
      <Link
        href={"/train"}
        className={
          pathName === "/train" ? styles.nav_item_select : styles.nav_item
        }
      >
        <Image
          className={styles.nav_icon}
          src={pathName === "/train" ? homeSelect : home}
          alt="홈"
        />
        홈
      </Link>
      <Link
        href={"/report"}
        className={
          pathName === "/report" ? styles.nav_item_select : styles.nav_item
        }
      >
        <Image
          className={styles.nav_icon}
          src={pathName === "/report" ? reportSelect : report}
          alt="리포트"
        />
        리포트
      </Link>
      <Link
        href={"/dream"}
        className={
          pathName === "/dream" ? styles.nav_item_select : styles.nav_item
        }
      >
        <Image
          className={styles.nav_icon}
          src={pathName === "/dream" ? dreamSelect : dream}
          alt="꿈"
        />
        꿈
      </Link>
      <Link
        href={"/friend"}
        className={
          pathName === "/friend" ? styles.nav_item_select : styles.nav_item
        }
      >
        <Image
          className={styles.nav_icon}
          src={pathName === "/friend" ? friendSelect : friend}
          alt="친구"
        />
        친구
      </Link>
      <Link
        href={"/collection"}
        className={
          pathName === "/collection" ? styles.nav_item_select : styles.nav_item
        }
      >
        <Image
          className={styles.nav_icon}
          src={pathName === "/collection" ? collectionSelect : collection}
          alt="설정"
        />
        컬렉션
      </Link>
    </ul>
  );
}
