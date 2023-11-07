package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.entity.Bookmark;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import com.ssafy.cozytrain.api.entity.key.BookmarkKey;
import com.ssafy.cozytrain.api.repository.BookmarkRepository;
import com.ssafy.cozytrain.api.service.BookmarkService;
import com.ssafy.cozytrain.api.service.elastic.CaffeineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final CaffeineService caffeineService;
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
    public List<CaffeineDocument> getBookmark(Long memberId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByBookmarkKeyMemberId(memberId);
        log.info("member :  " + memberId);
        log.info("bk: " + bookmarkList.get(0).toString());
        List<CaffeineDocument> caffeineDocuments = new ArrayList<>();
        return bookmarkList.stream().map((item) -> {
            log.info("item: " + item.toString());
                    log.info("caf: " + caffeineService.getCaffeineInfo(item.getBookmarkKey().getElsId()).toString());
                    return CaffeineDocument.builder().caffeineDocument(caffeineService.getCaffeineInfo(item.getBookmarkKey().getElsId())).build();
                })
                .collect(Collectors.toList());
    }


    public Boolean deleteMemberBookmark(Member member) {
        bookmarkRepository.deleteAllByBookmarkKeyMemberId(member.getMemberId());
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteBookmark(Member member, String elsId) {
        bookmarkRepository.deleteByBookmarkKeyMemberIdAndBookmarkKeyElsId(member.getMemberId(), elsId);
        return true;
    }
}
