"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";

import getFetch from "@/services/getFetch";
import PrimaryButton from "@/components/button/PrimaryButton";
import styles from "./FriendList.module.css";

export default function FriendList() {
  const router = useRouter();
  const [friendList, setFriendList] = useState([]);

  useEffect(() => {
    friendListReq();
  }, []);

  const friendListReq = async () => {
    const data = await getFetch("friend");
    console.log(data.response);
    setFriendList(data.response);
  };

  const handleRedirect = (friend) => {
    const { chatRoomId, friendId, memberId, profileImg, friendNickname } =
      friend;
    router.push(
      `/train/friend/${chatRoomId}?memberId=${memberId}&&memberImg=${profileImg}&&memberName=${friendNickname}`
    );
  };

  return (
    <div className={styles.topContainer}>
      {friendList.map((request, index) => {
        return (
          <div className={styles.middleContainer} key={index}>
            <div className={styles.profileContainer}>
              <div className={styles.wrapContainer}>
                <Image
                  src={request.profileImg}
                  width={50}
                  height={50}
                  className={styles.profile}
                  alt="프로필사진"
                />
                <div className={styles.fontContainer}>
                  <div>
                    #{index + 1} {request.friendNickname}
                  </div>
                  <div className={styles.fontWrap}>
                    <div className={styles.boldFont}>위치&nbsp;</div>
                    <div>
                      {request.trainInfo.countryKor}&nbsp;
                      {request.trainInfo.regionKor}
                    </div>
                  </div>
                </div>
              </div>
              <PrimaryButton
                height={"60%"}
                onClick={() => handleRedirect(request)}
              >
                메시지
              </PrimaryButton>
            </div>
          </div>
        );
      })}
    </div>
  );
}
