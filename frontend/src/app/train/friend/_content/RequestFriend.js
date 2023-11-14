import { useState, useEffect } from "react";
import Image from "next/image";

import PrimaryButton from "@/components/button/PrimaryButton";
import SecondaryButton from "@/components/button/SecondaryButton";
import styles from "./RequestFriend.module.css";
import getFetch from "@/services/getFetch";
import patchFetch from "@/services/patchFetch";
import deleteFetch from "@/services/deleteFetch";

export default function RequestFreind() {
  const [friendRequest, setFriendRequest] = useState([]);
  const [sendRequest, setSendRequest] = useState([]);

  useEffect(() => {
    getFriendReq();
    sendFriendReq();
  }, []);

  const getFriendReq = async () => {
    const data = await getFetch("friend/received-list");
    console.log("get: " + data.response);
    setFriendRequest(data.response);
  };

  const sendFriendReq = async () => {
    const data = await getFetch("friend/send-list");
    console.log("send: " + data.response);
    setSendRequest(data.response);
  };

  const patchFriendReq = async (friendId) => {
    console.log(friendId);
    try {
      const data = await patchFetch("friend", { friendId });
      console.log(data);
      getFriendReq();
    } catch (e) {
      console.log(e);
    }
  };

  const deleteFriendReq = async (friendId) => {
    console.log(friendId);
    try {
      const data = await deleteFetch("friend/" + friendId);
      console.log(data);
      sendFriendReq();
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div>
      <div className={styles.topFont}>받은 요청</div>

      {friendRequest.map((request, index) => {
        return (
          <div className={styles.middleContainer} key={index}>
            <div className={styles.profileContainer}>
              <div className={styles.profileContainerLeft}>
                <Image
                  src={request.profileImg}
                  width={50}
                  height={50}
                  className={styles.profile}
                  alt="프로필사진"
                />
                <div className={styles.fontContainer}>
                  <div>{request.friendNickname}</div>
                  <div className={styles.fontWrap}>
                    <div className={styles.boldFont}>위치&nbsp;</div>
                    <div>
                      {request.trainInfo.countryKor}&nbsp;
                      {request.trainInfo.regionKor}
                    </div>
                  </div>
                </div>
              </div>
              <PrimaryButton onClick={() => patchFriendReq(request.friendId)}>
                수락
              </PrimaryButton>
            </div>
          </div>
        );
      })}

      <div className={styles.topFont}>보낸 요청</div>

      {sendRequest.map((request, index) => {
        return (
          <div className={styles.middleContainer} key={index}>
            <div className={styles.profileContainer}>
              <div className={styles.profileContainerLeft}>
                <Image
                  src={request.profileImg}
                  width={50}
                  height={50}
                  className={styles.profile}
                  alt="프로필사진"
                />
                <div className={styles.fontContainer}>
                  <div>{request.friendNickname}</div>
                  <div className={styles.fontWrap}>
                    <div className={styles.boldFont}>위치&nbsp;</div>
                    <div>
                      {request.trainInfo.countryKor}&nbsp;
                      {request.trainInfo.regionKor}
                    </div>
                  </div>
                </div>
              </div>
              <SecondaryButton
                onClick={() => deleteFriendReq(request.friendId)}
              >
                취소
              </SecondaryButton>
            </div>
          </div>
        );
      })}
    </div>
  );
}
