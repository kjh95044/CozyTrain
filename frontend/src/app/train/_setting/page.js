import { useState } from "react";

import DeleteAccout from "./DeleteAccout";
import CloseButton from "@/components/button/CloseButton";
import useStore from "@/store/useStore";
import styles from "./page.module.css";

export default function Page({ onClick }) {
  const [deleteAcount, setDeleteAcount] = useState(false);
  const { memberName, memberProfileImg } = useStore();

  return (
    <>
      <div className={styles.closeBtn}>
        <CloseButton onClick={onClick} />
      </div>
      <div className={styles.title}>설정</div>
      <div className={styles.userInfo}>
        <div>{memberProfileImg}</div>
        <div>{memberName}</div>
      </div>
      <div className={styles.content}></div>
      <div className={styles.footer}>
        <div>로그아웃</div>
        <div onClick={() => setDeleteAcount(true)}>회원탈퇴</div>
      </div>

      {deleteAcount && <DeleteAccout />}
    </>
  );
}
