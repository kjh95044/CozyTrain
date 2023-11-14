import Image from "next/image";

import PrimaryButton from "@/components/button/PrimaryButton";

export default function BookMarkList() {
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
}
