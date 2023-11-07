package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;

import java.util.List;

public interface BookmarkService {
    boolean saveBookmark(BookmarkDto bookmarkDto, Long memberId);
    List<CaffeineDocument> getBookmark(Long memberId);
    Boolean deleteMemberBookmark(Member member);
    Boolean deleteBookmark(Member member, String elsId);
}
