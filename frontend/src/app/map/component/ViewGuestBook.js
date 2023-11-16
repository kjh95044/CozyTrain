'use client'

import { useEffect, useState } from "react"
import styles from "./ViewGuestBook.module.css"
import Image from "next/image";
import Check from "@/components/Lottie/Check";
import Toast from "@/components/Toast";

import getFetch from "@/services/getFetch"


export default function ViewGuestBook({countryId}) {

    const [guestBookContent, setGuestBookContent] = useState("");
    const [writerId, setWriterId] = useState(0);
    const [writerNickname, setWriterNickname] = useState("");
    const [writerImgAddress, setWriterImgAddress] = useState("");

    const [isLoading, setIsLoading] = useState(true);

    const getRandomGuestBook = async () => {
        const data = await getFetch(`guest-book/${countryId}`)
        setGuestBookContent(data.response.guestBookContent)
        setWriterId(data.response.memberId);

        setTimeout(() => {
            setIsLoading(false);
        }, 1800)
    }

    const getWriterInfo = async () => {
        const data = await getFetch(`member/info/${writerId}`)
        setWriterNickname(data.response.memberName);
        setWriterImgAddress(data.response.memberImageUrl);
    }

    useEffect(() => {
        getRandomGuestBook();
    }, []);

    useEffect(() => {
        if(writerId !== 0) {
            getWriterInfo();
        }
    }, [writerId]);


    return (
        <div className={styles.container}>
            {!isLoading ? (
                <>
                <div className={styles.userProfile}>
                    <Image
                        className={styles.profileImg}
                        src={writerImgAddress}
                        width={32}
                        height={32}
                        alt="사용자 이미지"
                    />
                    <span className={styles.nickname}>{writerNickname}</span>
                </div>
                <textarea 
                    className={styles.speechBubble}
                    value={guestBookContent}
                    readOnly> 
                </textarea>                
                </>
            ) : (
                <div className={styles.loading}>
                    랜덤으로 방명록을 가져오고 있어요 😎
                    <Toast>
                        <Check />
                    </Toast>
                </div>
            )}
        </div>

    )
}