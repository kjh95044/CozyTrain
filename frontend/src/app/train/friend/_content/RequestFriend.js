import { useState, useEffect } from "react";
import Image from "next/image";

import styles from "./RequestFriend.module.css";
import getFetch from "@/services/getFetch";
import patchFetch from "@/services/patchFetch";
import deleteFetch from "@/services/deleteFetch";
import noResponse from "/public/images/no-response.png";
import FriendItem from "./FriendItem";

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
      <FriendItem
        friendList={friendRequest}
        isPrimary={true}
        class={"recieve"}
        text={"수락"}
        onClick={patchFriendReq}
      ></FriendItem>

      {friendRequest.length == 0 && (
        <Image
          src={noResponse}
          className={styles.noResponse}
          alt="받은 요청이 없습니다."
        />
      )}
      <div className={styles.topFont}>보낸 요청</div>

      <FriendItem
        friendList={sendRequest}
        isPrimary={false}
        text={"취소"}
        onClick={deleteFriendReq}
      ></FriendItem>

      {sendRequest.length == 0 && (
        <Image
          src={noResponse}
          className={styles.noResponse}
          alt="받은 요청이 없습니다."
        />
      )}
    </div>
  );
}
