"use client"

import styles from "./SubmitGuestbook.module.css"
import PrimaryButton from "@/components/button/PrimaryButton"
import postFetch from "@/services/postFetch";

import { useState } from "react";


export default function SubmitGuestbook({onClick}) {

    const [content, setContent] = useState("");

    const contentChange = (event) => {
      setContent(event.target.value);
    };

    const handleSubmit = async (e) => {
        const postData = {
            countryId: 1,
            guestBookContent: content,
        }

        const respData = await postFetch("guest-book", postData);
        if(respData.success) {
            console.log(respData);
        }
    }

    const handleButtonClick = () => {
        console.log(content);
        handleSubmit();

        if(onClick) {
            onClick();
        }
    }

    return (
        <div>
            <textarea 
                className={styles.inputContent}
                name="content"
                placeholder="방명록을 남겨주세요!"
                onChange={contentChange}
            ></textarea>
            <br/>
            <PrimaryButton onClick={handleButtonClick}>등록</PrimaryButton>
        </div>
    )
}