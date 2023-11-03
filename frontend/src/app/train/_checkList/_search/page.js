import { useState } from "react";

import AutoSearchList from "./AutoSearchList";
import SearchResult from "./SearchResult";
import Search from "@/components/icons/Search";
import getFetch from "@/services/getFetch";
import styles from "./page.module.css";

export default function Page() {
  const [data, setData] = useState([]);
  const [onAutoSearch, setOnAutoSearch] = useState(true);
  const [autoSearchValue, setAutoSearchValue] = useState([]);

  const handleAutoSearchValue = async (e) => {
    const resp = await getFetch("caffeine/search", {
      searchName: e.target.value,
      size: 5,
    });

    const set = new Set(resp.response.content.map((item) => item.name));
    setAutoSearchValue(Array.from(set));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    searchDrink(e.target.getElementsByTagName("input")[0].value);
  };

  const searchDrink = async (e) => {
    const resp = await getFetch("caffeine/search", {
      searchName: e,
    });

    console.log(resp.response);

    setData(resp.response.content);
  };

  return (
    <>
      <div
        className={styles.searchContainer}
        onFocus={() => {
          setOnAutoSearch(true);
        }}
        onBlur={() => {
          setOnAutoSearch(false);
        }}
      >
        <div className={styles.searchBar}>
          <form className={styles.form} onSubmit={handleSubmit}>
            <input
              className={styles.input}
              type="text"
              placeholder="오늘은 무엇을 마셨나요?"
              onChange={handleAutoSearchValue}
            />
            <button type="submit" className={styles.btn} />
          </form>
          <div className={styles.serachIcon}>
            <Search />
          </div>
        </div>

        {onAutoSearch && <AutoSearchList autoSearchValue={autoSearchValue} />}
      </div>

      <SearchResult items={data} />
    </>
  );
}
