"use client"

import Check from "@/components/Lottie/Check";
import Toast from "@/components/Toast";
import styles from "./SubmitGuestbook.module.css"
import PrimaryButton from "@/components/button/PrimaryButton"
import postFetch from "@/services/postFetch";

import { useState } from "react";


export default function SubmitGuestbook({countryId, onClick}) {

    const [showToast, setShowToast] = useState(false);
    const [content, setContent] = useState("");

    const contentChange = (event) => {
      setContent(event.target.value);
    };

    const handleSubmit = async (e) => {
        const postData = {
            countryId: countryId,
            guestBookContent: content,
        }

        const respData = await postFetch("guest-book", postData);
        if (respData.success) {
            setShowToast(true);
            setTimeout(() => {
              setShowToast(false);
              if(onClick) {
                onClick();
            }
            }, 1600);
          }
    }

    const handleButtonClick = () => {
        console.log(content);
        handleSubmit();
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
            {showToast ? (
                <>
                <Toast>
                    <Check />
                </Toast>
                <div className={styles.gapContent}>
                </div>
                </>
            ) : (
                <PrimaryButton 
                className={styles.btn}
                onClick={handleButtonClick}>등록</PrimaryButton>
            )}
            
        </div>
    )
}