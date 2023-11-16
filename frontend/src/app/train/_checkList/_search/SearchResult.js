import Image from "next/image";
import { useState } from "react";

import Check from "@/components/Lottie/Check";
import Toast from "@/components/Toast";
import PrimaryButton from "@/components/button/PrimaryButton";
import deleteFetch from "@/services/deleteFetch";
import postFetch from "@/services/postFetch";
import starSelect from "#/icons/star-select.png";
import star from "#/icons/star.png";
import styles from "./SearchResult.module.css";

export default function SearchResult(props) {
  const [showToast, setShowToast] = useState(false);
  const [expandedItem, setExpandedItem] = useState(null);

  const handleBookMarkClick = async (e, isBookmarked) => {
    const elsId = e.target.getAttribute("value");

    if (isBookmarked) await removeBookMark(elsId);
    else await createBookmark(elsId);

    props.getBookMark();
    props.getBookmarkItem();
  };

  const createBookmark = async (elsId) => {
    await postFetch("bookmark", elsId);
  };

  const removeBookMark = async (elsId) => {
    await deleteFetch("bookmark/" + elsId);
  };

  const handleContentClick = (item) => {
    if (expandedItem === item.id) {
      setExpandedItem(null);
    } else {
      setExpandedItem(item.id);
    }
  };

  const addTodayList = async (elsId) => {
    const resp = await postFetch("check-list", { elsId });

    if (resp.response === 1) {
      setShowToast(true);
      setTimeout(() => {
        setShowToast(false);
      }, 1600);
    }
  };

  return (
    <div className={styles.items}>
      {props.items.map((item, idx) => {
        const isExpanded = expandedItem === item.id;
        const isBookmarked = props.bookMark.some(
          (bookMark) => item.id === bookMark.elsId
        );

        return (
          <div key={idx}>
            <div className={styles.item}>
              <div
                className={styles.content}
                onClick={() => handleContentClick(item)}
              >
                {item.name}({item.made})
              </div>

              <Image
                className={styles.icon}
                src={isBookmarked ? starSelect : star}
                alt="즐겨찾기"
                value={item.id}
                onClick={(e) => handleBookMarkClick(e, isBookmarked)}
              ></Image>
            </div>

            {isExpanded && (
              <div className={styles.detailContainer}>
                <div className={styles.detailContent}>
                  <div>용량: {item.amount}ml</div>
                  <div>칼로리: {item.energy}kcal</div>
                  <div>당류: {item.suger}g</div>
                </div>
                <PrimaryButton
                  onClick={() => {
                    addTodayList(item.id);
                  }}
                >
                  오늘 마신 음료 추가
                </PrimaryButton>
              </div>
            )}
          </div>
        );
      })}

      {showToast && (
        <Toast>
          <Check />
        </Toast>
      )}
    </div>
  );
}
