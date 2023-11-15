import { useState, useEffect } from "react";

import AutoSearchList from "./AutoSearchList";
import SearchResult from "./SearchResult";
import Search from "@/components/icons/Search";
import getFetch from "@/services/getFetch";
import styles from "./page.module.css";

export default function Page(props) {
  const [data, setData] = useState([]);
  const [onAutoSearch, setOnAutoSearch] = useState(true);
  const [autoSearchValue, setAutoSearchValue] = useState([]);
  const [bookMark, setBookMark] = useState([]);
  const [bookMarkItem, setBookmarkItem] = useState([]);
  const [isSearched, setIsSearched] = useState(false);

  const handleAutoSearchValue = async (e) => {
    const resp = await getFetch("caffeine/search", {
      searchName: e.target.value,
      size: 5,
    });

    const set = new Set(resp.response.content.map((item) => item.name));
    setAutoSearchValue(Array.from(set));
    setOnAutoSearch(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setIsSearched(true);
    searchDrink(e.target.getElementsByTagName("input")[0].value);
    setOnAutoSearch(false);
  };

  const handleAutoSearchSubmit = () => {
    setIsSearched(true);

    setTimeout(() => {
      setOnAutoSearch(false);
    }, 1);
  };

  const searchDrink = async (searchName) => {
    const resp = await getFetch("caffeine/search", {
      searchName,
    });

    setData(resp.response.content);
  };

  const getBookMark = async () => {
    try {
      const resp = await getFetch("bookmark");

      setBookMark(resp.response);
    } catch (error) {
      console.error(error);
    }
  };

  const getBookmarkItem = async () => {
    try {
      const resp = await getFetch("bookmark/item");
      setBookmarkItem(resp.response);
    } catch {
      setBookmarkItem([]);
    }
  };

  useEffect(() => {
    getBookMark();
    getBookmarkItem();
  }, []);

  return (
    <>
      <div
        className={styles.searchContainer}
        onFocus={() => {
          setOnAutoSearch(true);
        }}
        onBlur={handleAutoSearchSubmit}
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

        {onAutoSearch && (
          <AutoSearchList
            autoSearchValue={autoSearchValue}
            handleAutoSearchSubmit={handleAutoSearchSubmit}
            searchDrink={searchDrink}
          />
        )}
      </div>

      <SearchResult
        items={isSearched ? data : bookMarkItem}
        bookMark={bookMark}
        getBookMark={getBookMark}
        getBookmarkItem={getBookmarkItem}
      />
    </>
  );
}
