import { useState, useEffect } from "react";

import findDrinkById from "../findDrinkById";
import PrimaryButton from "@/components/button/PrimaryButton";
import deleteFetch from "@/services/deleteFetch";
import getFetch from "@/services/getFetch";
import styles from "./page.module.css";

export default function Page() {
  const [checkList, setCheckList] = useState([]);

  const getCheckList = async () => {
    const resp = await getFetch("check-list");

    if (!resp.response) {
      setCheckList([]);
      return;
    }

    const data = resp.response.checkListDtoList;
    const promises = data.map(async (e) => {
      const drinkName = await findDrinkById(e.elsId);
      return { ...e, drinkName };
    });

    const newArr = await Promise.all(promises);
    setCheckList(newArr);
  };

  const removeCheckListItem = async (checkListId) => {
    await deleteFetch(`check-list/${checkListId}`);
    getCheckList();
  };

  useEffect(() => {
    getCheckList();
  }, []);

  return (
    <>
      <div className={styles.title}>
        오늘 섭취한 <b>음료</b>입니다.
      </div>
      <div className={styles.itemContainer}>
        {!checkList.length && (
          <div className={styles.defalutText}>
            검색에서 음료를 추가해주세요.
          </div>
        )}
        {checkList.map((e) => {
          return (
            <div key={e.checkListId} className={styles.item}>
              <div>{e.drinkName}</div>
              <div
                onClick={() => {
                  removeCheckListItem(e.checkListId);
                }}
              >
                <PrimaryButton>삭제</PrimaryButton>
              </div>
            </div>
          );
        })}
      </div>
    </>
  );
}
