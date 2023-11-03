import Image from "next/image";

import postFetch from "@/services/postFetch";
import starSelect from "#/icons/star-select.png";
import star from "#/icons/star.png";
import styles from "./SearchResult.module.css";

export default function SearchResult(props) {
  const createBookmark = async (e) => {
    const elsId = e.target.getAttribute("value");

    console.log(elsId);

    const resp = await postFetch("bookmark", elsId);
  };

  return (
    <div className={styles.items}>
      {props.items.map((item, idx) => (
        <div className={styles.item} key={idx}>
          <div className={styles.radioBtn}></div>
          <div className={styles.content}>{item.name}</div>
          <Image
            className={styles.icon}
            src={star}
            alt="즐겨찾기"
            value={item.id}
            onClick={createBookmark}
          ></Image>
        </div>
      ))}
    </div>
  );
}
