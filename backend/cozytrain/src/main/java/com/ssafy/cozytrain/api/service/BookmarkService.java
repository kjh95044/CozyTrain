package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.BookmarkDto;

public interface BookmarkService {
    boolean saveBookmark(BookmarkDto bookmarkDto);
}
