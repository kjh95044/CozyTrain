import { useState } from "react";
import Image from "next/image";

import PrimaryButton from "@/components/button/PrimaryButton";
import styles from "./AddFriend.module.css";
import getFetch from "@/services/getFetch";
import postFetch from "@/services/postFetch";

export default function AddFriend() {
  const [friendAdd, setFriendAdd] = useState([]);
  const [friendLoginId, setFriendLoginId] = useState("");

  const addFriendReq = async () => {
    const data = await getFetch(`friend/search`, { friendLoginId });
    setFriendAdd(data.response);
  };

  const handleSubmit = async (memberId) => {
    const postData = {
      memberId: memberId,
    };

    const respData = await postFetch("friend", postData);
    if (respData.success) {
      alert("요청 완료");
      addFriendReq(friendLoginId);
    }
  };

  return (
    <div>
      <div id={styles.topContainer}>
        <input
          type="text"
          className={styles.inputBox}
          placeholder="닉네임을 입력해주세요."
          onChange={(e) => setFriendLoginId(e.target.value)}
        />
        <button className={styles.buttonBox} onClick={() => addFriendReq()}>
          검색
        </button>
      </div>
      {friendAdd.map((friend, index) => {
        return (
          <div className={styles.middleContainer} key={index}>
            <div className={styles.profileContainer}>
              <div className={styles.profileContainerLeft}>
                <Image
                  src={friend.profileImg}
                  width={50}
                  height={50}
                  className={styles.profile}
                  alt="프로필사진"
                />
                <div className={styles.fontContainer}>
                  <div>{friend.friendNickname}</div>
                  <div className={styles.fontWrap}>
                    <div className={styles.boldFont}>위치&nbsp;</div>
                    <div>
                      {friend.trainInfo.countryKor} &nbsp;
                      {friend.trainInfo.regionKor}
                    </div>
                  </div>
                </div>
              </div>
              <PrimaryButton onClick={() => handleSubmit(friend.memberId)}>
                요청
              </PrimaryButton>
            </div>
          </div>
        );
      })}
    </div>
  );
}
