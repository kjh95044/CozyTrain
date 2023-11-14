package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Bookmark;
import com.ssafy.cozytrain.api.entity.key.BookmarkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkKey> {
    List<Bookmark> findAllByBookmarkKey(BookmarkKey bookmarkKey);
    List<Bookmark> findAllByBookmarkKeyMemberId(Long memberId);

    void deleteAllByBookmarkKeyMemberId(Long memberId);
}
