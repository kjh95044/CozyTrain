import { useState, useEffect } from "react";
import Image from "next/image";

import getFetch from "@/services/getFetch";
import PrimaryButton from "@/components/button/PrimaryButton";
import styles from "./FriendList.module.css";

export default function FriendList() {
  const [friendList, setFriendList] = useState([]);

  useEffect(() => {
    friendListReq();
  }, []);

  const friendListReq = async () => {
    const data = await getFetch("friend");
    console.log(data.response);
    setFriendList(data.response);
  };

  return (
    <div className={styles.topContainer}>
      {friendList.map((request, index) => {
        return (
          <div className={styles.middleContainer} key={index}>
            <div className={styles.profileContainer}>
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
                    {request.trainInfo.countryKor} &nbsp;
                    {request.trainInfo.regionKor}
                  </div>
                </div>
              </div>
              <PrimaryButton>메시지</PrimaryButton>
            </div>
          </div>
        );
      })}
    </div>
  );
}
