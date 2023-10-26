package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.entity.Bookmark;
import com.ssafy.cozytrain.api.entity.key.BookmarkKey;
import com.ssafy.cozytrain.api.repository.BookmarkRepository;
import com.ssafy.cozytrain.api.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBookmark(BookmarkDto bookmarkDto) {
        // TODO: 로그인 완성되면, memberId 변경 로직
        BookmarkKey bookmarkKey = BookmarkKey.builder()
                .elsId(bookmarkDto.getElsId())
                .memberId(1L)
                .build();
        Bookmark bookmark = Bookmark.builder().bookmarkKey(bookmarkKey).build();
        Bookmark saveBookmark = bookmarkRepository.save(bookmark);
        if(saveBookmark != null) return true;
        return false;
    }
}
