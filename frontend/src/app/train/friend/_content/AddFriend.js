import { useState } from "react";
import styles from "./AddFriend.module.css";
import getFetch from "@/services/getFetch";
import postFetch from "@/services/postFetch";
import FriendItem from "./FriendItem";

export default function AddFriend() {
  const [friendAdd, setFriendAdd] = useState([]);
  const [friendLoginId, setFriendLoginId] = useState("");

  const addFriendReq = async () => {
    console.log(friendLoginId);
    const data = await getFetch(`friend/search`, { friendLoginId });
    console.log(data.response);
    setFriendAdd(data.response);
  };

  const handleSubmit = async (memberId) => {
    const postData = {
      memberId: memberId,
    };

    const respData = await postFetch("friend", postData);
    if (respData.success) {
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
      <FriendItem
        friendList={friendAdd}
        isPrimary={true}
        class={"add"}
        text={"요청"}
        onClick={handleSubmit}
      ></FriendItem>
    </div>
  );
}
