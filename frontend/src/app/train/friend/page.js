"use client";
import { useState } from "react";

import Background from "@/components/Background";
import Header from "@/components/Header";
import styles from "./page.module.css";

import RequestFreind from "./_content/RequestFriend";
import FriendList from "./_content/FriendList";
import AddFriend from "./_content/AddFriend";

export default function Friend() {
  const [location, setLocation] = useState(0);

  return (
    <div>
      <Background />
      <Header>친구</Header>
      <div className={styles.topContainer}>
        <div className={styles.buttonContainer}>
          <button
            className={
              location === 0
                ? styles.primaryHeaderButton
                : styles.secondaryHeaderButton
            }
            onClick={() => setLocation(0)}
          >
            {" "}
            요청 목록{" "}
          </button>
          <button
            className={
              location === 1
                ? styles.primaryHeaderButton
                : styles.secondaryHeaderButton
            }
            onClick={() => setLocation(1)}
          >
            {" "}
            친구 추가
          </button>
          <button
            className={
              location === 2
                ? styles.primaryHeaderButton
                : styles.secondaryHeaderButton
            }
            onClick={() => setLocation(2)}
          >
            {" "}
            랭킹
          </button>
        </div>
        {location === 0 && <RequestFreind></RequestFreind>}
        {location === 1 && <AddFriend></AddFriend>}
        {location === 2 && <FriendList></FriendList>}
      </div>
    </div>
  );
}
