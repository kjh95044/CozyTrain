import { useState, useRef } from "react";
import { useRouter } from "next/navigation";
import Image from "next/image";

import patchFormFetch from "@/services/patchFormFetch";
import profileImg from "#/images/profile-img.png";
import DeleteAccout from "./DeleteAccout";
import PrimaryButton from "@/components/button/PrimaryButton";
import useStore from "@/store/useStore";
import styles from "./page.module.css";

export default function Page() {
  const [deleteAcount, setDeleteAcount] = useState(false);
  const { memberName, memberProfileImg, region, dist, logout, setMemberImg } =
    useStore();
  const router = useRouter();
  const inputRef = useRef();

  const handleLogoutBtn = () => {
    document.cookie = "accessToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    document.cookie = "id=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    document.cookie = "pw=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    logout();
    router.push("/login");
  };

  const handleInputChange = async () => {
    if (!inputRef.current.files) return;

    const image = inputRef.current.files[0];
    const data = await patchFormFetch("member/image", image);
    setMemberImg(data.response.memberImgUrl);
  };

  const handleDeleteAcountFalse = () => {
    setDeleteAcount(false);
  };

  return (
    <div className={styles.container}>
      <div className={styles.userProfile}>
        <input
          ref={inputRef}
          type="file"
          style={{ display: "none" }}
          onChange={handleInputChange}
        />

        <Image
          className={styles.profileImg}
          src={memberProfileImg ? memberProfileImg : profileImg}
          alt="프로필 사진"
          width={100}
          height={100}
          onClick={() => inputRef.current.click()}
        />

        <div className={styles.memberName}>
          <div>{memberName}</div>
        </div>
      </div>
      <div className={styles.userInfo}>
        <div className={styles.content}>
          <div className={styles.text}>총 이동 거리</div>
          <div className={styles.text}>{dist}km</div>
        </div>
        <div className={styles.content}>
          <div className={styles.text}>현재 위치</div>
          <div className={styles.text}>{region}</div>
        </div>
      </div>
      <div className={styles.footer}>
        <PrimaryButton onClick={handleLogoutBtn}>로그아웃</PrimaryButton>
        <PrimaryButton onClick={() => setDeleteAcount(true)}>
          회원탈퇴
        </PrimaryButton>
      </div>

      {deleteAcount && (
        <DeleteAccout handleDeleteAcountFalse={handleDeleteAcountFalse} />
      )}
    </div>
  );
}
