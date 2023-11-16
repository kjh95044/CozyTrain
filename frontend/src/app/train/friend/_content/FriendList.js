"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";

import getFetch from "@/services/getFetch";
import styles from "./FriendList.module.css";

import FriendItem from "./FriendItem";

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
      <FriendItem
        friendList={friendList}
        isPrimary={true}
        class={"list"}
        text={"메시지"}
        onClick={handleRedirect}
      ></FriendItem>
    </div>
  );
}
