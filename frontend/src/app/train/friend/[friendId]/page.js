"use client";

import Image from "next/image";
import { useState, useEffect, useRef, useLayoutEffect } from "react";
import { useRouter, useSearchParams, usePathname } from "next/navigation";

import Background from "@/components/Background";
import Header from "./Title";
import getFetch from "@/services/getFetch";
import multipartPostFetch from "@/services/friend/multipartPostFetch";

import styles from "./page.module.css";
import play from "#/icons/play.png";
import sound from "/public/icons/play-sound.png";
import playMic from "/public/icons/play-mic.png";
import stopMic from "/public/icons/stop-mic.png";
import offMic from "/public/icons/play-off.png";
import send from "/public/icons/play-send.png";
import Progress from "@/components/Lottie/Progress";

export default function Message() {
  const ref = useRef(null);
  const router = useRouter();
  const params = useSearchParams();

  const chatRoomId = usePathname().substring(14);
  const friendMemberId = params.get("memberId");
  const memberImg = params.get("memberImg");
  const memberName = params.get("memberName");
  const [messageList, setmessageList] = useState([]);

  const [onRec, setOnRec] = useState(false);
  const [pause, setPause] = useState(false);
  const [isRecording, setIsRecording] = useState(false);

  const [stream, setStream] = useState();
  const [media, setMedia] = useState();
  const [source, setSource] = useState();
  const [analyser, setAnalyser] = useState();
  const [audioUrl, setAudioUrl] = useState();

  useEffect(() => {
    messageListReq();
  }, []);

  useLayoutEffect(() => {
    console.log(ref.current);
    if (ref.current) {
      ref.current.scrollTop = ref.current.scrollHeight;
    }
  }, [messageList]);

  const messageListReq = async () => {
    const data = await getFetch(`message/${friendMemberId}`);
    console.log(data.response);
    setmessageList(data.response);
    console.log(friendMemberId);
  };

  const playAudio = (audioUrl) => {
    const audio = new Audio(audioUrl);
    audio.play();
  };

  const onRecAudio = () => {
    setOnRec(true);
    setIsRecording(true);
    const audioCtx = new (window.AudioContext || window.webkitAudioContext)();
    const analyser = audioCtx.createScriptProcessor(0, 1, 1);
    setAnalyser(analyser);

    function makeSound(stream) {
      const source = audioCtx.createMediaStreamSource(stream);
      setSource(source);
      source.connect(analyser);
      analyser.connect(audioCtx.destination);
    }

    navigator.mediaDevices.getUserMedia({ audio: true }).then((stream) => {
      const mediaRecorder = new MediaRecorder(stream);
      mediaRecorder.start();
      setStream(stream);
      setMedia(mediaRecorder);
      makeSound(stream);
    });
  };
  const offRecAudio = () => {
    console.log(pause);
    setPause(true);
    setIsRecording(false);
    media.ondataavailable = function (e) {
      setAudioUrl(e.data);
    };

    stream.getAudioTracks().forEach(function (track) {
      track.stop();
    });

    media.stop();
    analyser.disconnect();
    source.disconnect();
  };

  const onSubmitAudioFile = async () => {
    if (audioUrl) {
      console.log(URL.createObjectURL(audioUrl));
    }
    const sound = new File([audioUrl], "soundBlob", {
      lastModified: new Date().getTime(),
      type: "audio/*",
    });

    const messageReq = {
      chatRoomId,
    };

    const formData = new FormData();
    formData.append("file", sound);
    formData.append(
      "messageReqDto",
      new Blob([JSON.stringify(messageReq)], { type: "application/json" })
    );

    multipartPostFetch("message", formData)
      .then(() => {
        alert("메시지 전송");
        messageListReq();
      })
      .catch((e) => console.log(e));
  };

  return (
    <div>
      <Background />
      <Header>{memberName}</Header>
      <div className={styles.messageContainer} ref={ref}>
        {messageList
          .slice()
          .reverse()
          .map((message, index) => (
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
                        <div
                          className={styles.playContainer}
                          onClick={() => playAudio(message.messageUrl)}
                        >
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
                      <div
                        className={styles.playContainer}
                        onClick={() => playAudio(message.messageUrl)}
                      >
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
                  );
                }
              })()}
            </div>
          ))}
      </div>

      <div className={styles.recordContainer}>
        {!onRec && (
          <div className={styles.playMicContainer} onClick={onRecAudio}>
            <Image src={playMic} width={24} height={24} alt="재생" />
          </div>
        )}
        {onRec && (
          <div>
            <div className={styles.progressContainer}>
              <Progress className={styles.progress} isPlaying={isRecording} />
              <Image
                src={send}
                width={18}
                height={18}
                alt="보내기"
                className={styles.send}
                onClick={onSubmitAudioFile}
              />
            </div>
            <div className={styles.stopContainer} onClick={offRecAudio}>
              <Image
                src={!pause ? stopMic : offMic}
                width={24}
                height={24}
                alt="멈춤"
              />
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
