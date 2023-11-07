import { useState } from "react";
import { useRouter } from "next/navigation";
import Image from "next/image";

import profileImg from "#/images/profile-img.png";
import DeleteAccout from "./DeleteAccout";
import CloseButton from "@/components/button/CloseButton";
import useStore from "@/store/useStore";
import styles from "./page.module.css";

export default function Page() {
  const [deleteAcount, setDeleteAcount] = useState(false);
  const { memberName, memberProfileImg } = useStore();
  const router = useRouter();

  const handleLogoutBtn = () => {
    router.push("/login");
  };

  return (
    <div>
      <div className={styles.userProfile}>
        <Image
          className={styles.profileImg}
          src={profileImg}
          alt="프로필 사진"
        />
        <div className={styles.memberName}>
          <div>아이디: juwon</div>
          <div>닉네임: {memberName}</div>
        </div>
      </div>
      <div className={styles.userInfo}>
        <div className={styles.content}>
          <div>총 이동 거리</div>
          <div>126km</div>
        </div>
        <div className={styles.content}>
          <div>총 이동 거리</div>
          <div>126km</div>
        </div>
        <div className={styles.content}>
          <div>총 이동 거리</div>
          <div>126km</div>
        </div>
      </div>
      <div className={styles.footer}>
        <div onClick={handleLogoutBtn}>로그아웃</div>
        <div onClick={() => setDeleteAcount(true)}>회원탈퇴</div>
      </div>

      {deleteAcount && <DeleteAccout />}
    </div>
  );
}
