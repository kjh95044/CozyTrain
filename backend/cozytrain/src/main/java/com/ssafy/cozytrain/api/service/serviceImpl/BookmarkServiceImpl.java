package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.entity.Bookmark;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.key.BookmarkKey;
import com.ssafy.cozytrain.api.repository.BookmarkRepository;
import com.ssafy.cozytrain.api.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBookmark(BookmarkDto bookmarkDto, Long memberId) {
        BookmarkKey bookmarkKey = BookmarkKey.builder()
                .elsId(bookmarkDto.getElsId())
                .memberId(memberId)
                .build();
        Bookmark bookmark = Bookmark.builder().bookmarkKey(bookmarkKey).build();
        Bookmark saveBookmark = bookmarkRepository.save(bookmark);
        if(saveBookmark != null) return true;
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookmarkDto> getBookmark(Long memberId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByBookmarkKeyMemberId(memberId);
        return bookmarkList.stream().map((item) -> {
                return BookmarkDto.builder().elsId(item.getBookmarkKey().getElsId()).build();
            })
            .collect(Collectors.toList());
    }

    public Boolean deleteMemberBookmark(Member member) {
        bookmarkRepository.deleteAllByBookmarkKeyMemberId(member.getMemberId());
        return true;
    }
}
