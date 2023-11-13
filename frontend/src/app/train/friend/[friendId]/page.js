"use client";

import Image from "next/image";
import { useState, useEffect, useRef, useLayoutEffect } from "react";
import { useRouter, useSearchParams, usePathname } from "next/navigation";

import Background from "@/components/Background";
import Header from "@/components/Header";
import getFetch from "@/services/getFetch";
import styles from "./page.module.css";
import play from "/public/icons/play.png";
import sound from "/public/icons/sound.png";

export default function Message() {
  const ref = useRef(null);
  const router = useRouter();
  const params = useSearchParams();

  const chatRoomId = usePathname().substring(14);
  const friendMemberId = params.get("memberId");
  const memberImg = params.get("memberImg");
  const memberName = params.get("memberName");
  const [messageList, setmessageList] = useState([]);

  useEffect(() => {
    messageListReq();
  }, []);

  useLayoutEffect(() => {
    if (ref) {
      ref.current.scrollIntoView({
        block: "end",
        inline: "nearest",
      });
    }
  }, [messageList]);

  const messageListReq = async () => {
    const data = await getFetch(`message/${friendMemberId}`);
    console.log(data.response);
    setmessageList(data.response);
    console.log(friendMemberId);
  };

  return (
    <div ref={ref}>
      <Background />
      <Header>{memberName}</Header>
      <div className={styles.messageContainer}>
        {messageList.map((message, index) => (
          <div key={index} className={styles.bubbleContainer}>
            {(() => {
              if (message.senderMemberId == friendMemberId) {
                return (
                  <div className={styles.senderContainer}>
                    <Image
                      src={memberImg}
                      width={48}
                      height={48}
                      alt="프로필 이미지"
                      className={styles.profileImg}
                    />
                    <div className={styles.leftBubble}>
                      <div className={styles.playContainer}>
                        <Image src={play} width={16} height={16} alt="재생" />
                        <Image
                          src={sound}
                          width={64}
                          height={20}
                          alt="사운드"
                        />
                      </div>
                      <div>
                        {new Date(message.createdAt).toLocaleTimeString(
                          "ko-KR",
                          {
                            hour: "numeric",
                            minute: "numeric",
                            hour12: false,
                          }
                        )}
                      </div>
                    </div>
                  </div>
                );
              } else {
                return (
                  <div className={styles.rightBubble}>
                    <div className={styles.playContainer}>
                      <Image src={play} width={16} height={16} alt="재생" />
                      <Image src={sound} width={64} height={20} alt="사운드" />
                    </div>
                    <div>
                      {new Date(message.createdAt).toLocaleTimeString("ko-KR", {
                        hour: "numeric",
                        minute: "numeric",
                        hour12: false,
                      })}
                    </div>
                  </div>
                );
              }
            })()}
          </div>
        ))}
      </div>

      <div className={styles.recordContainer}></div>
    </div>
  );
}
