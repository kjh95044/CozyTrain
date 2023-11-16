'use client'

import { useEffect, useState } from "react"
import styles from "./ViewGuestBook.module.css"
import Image from "next/image";

import profileImg from "#/images/profile-img.png";
import getFetch from "@/services/getFetch"


export default function ViewGuestBook({countryId}) {

    const [guestBookContent, setGuestBookContent] = useState("");
    const [writerId, setWriterId] = useState(0);

    const getRandomGuestBook = async () => {
        const data = await getFetch(`guest-book/${countryId}`)
        setGuestBookContent(data.response.guestBookContent)
        setWriterId(data.response.memberId);
        console.log(data);
    }

    useEffect(() => {
        getRandomGuestBook();
    }, []);


    return (
        <div className={styles.container}>
            <div className={styles.userProfile}>
                <Image
                    src={profileImg}
                    width={32}
                    height={32}
                    alt="사용자 이미지"
                />
                <span className={styles.nickname}>닉네임</span>
            </div>
            <textarea 
                className={styles.speechBubble}
                value={guestBookContent}
                readOnly> 
            </textarea>
        </div>

    )
}