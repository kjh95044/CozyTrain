import Image from "next/image";
import PrimaryButton from "@/components/button/PrimaryButton";
import SecondaryButton from "@/components/button/SecondaryButton";
import styles from "./FriendItem.module.css";

export default function FriendItem(props) {
  return (
    <div className={styles.itemContainer}>
      {props.friendList.map((request, index) => {
        return (
          <div className={styles.middleContainer} key={index}>
            <div className={styles.profileContainer}>
              <div className={styles.wrapContainer}>
                <Image
                  src={request.profileImg}
                  width={50}
                  height={50}
                  className={styles.profile}
                  alt="프로필사진"
                />
                <div className={styles.fontContainer}>
                  <div>
                    {props.class == "list" && `#${index + 1} `}

                    {request.friendNickname}
                  </div>
                  <div className={styles.fontWrap}>
                    <div className={styles.boldFont}>위치&nbsp;</div>
                    <div>
                      {request.trainInfo.countryKor}&nbsp;
                      {request.trainInfo.regionKor}
                    </div>
                  </div>
                </div>
              </div>
              {props.isPrimary && props.class == "list" && (
                <PrimaryButton
                  height={"60%"}
                  onClick={() => props.onClick(request)}
                >
                  {props.text}
                </PrimaryButton>
              )}
              {props.isPrimary && props.class == "add" && (
                <PrimaryButton
                  height={"60%"}
                  onClick={() => props.onClick(request.memberId)}
                >
                  {props.text}
                </PrimaryButton>
              )}
              {props.isPrimary && props.class == "recieve" && (
                <PrimaryButton
                  height={"60%"}
                  onClick={() => props.onClick(request.friendId)}
                >
                  {props.text}
                </PrimaryButton>
              )}
              {!props.isPrimary && (
                <SecondaryButton
                  height={"60%"}
                  onClick={() => props.onClick(request.friendId)}
                >
                  취소
                </SecondaryButton>
              )}
            </div>
          </div>
        );
      })}
    </div>
  );
}
