package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.BookmarkService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "북마크 생성")
    public ApiUtils.ApiResult<Boolean> createBookmark(@RequestHeader("Authorization") String header, @RequestBody @Valid BookmarkDto bookmarkDto){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(bookmarkService.saveBookmark(bookmarkDto, member.getMemberId()));
    }

    @GetMapping
    @Operation(summary = "북마크 조회")
    public ApiUtils.ApiResult<List<BookmarkDto>> createBookmark(@RequestHeader("Authorization") String header){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(bookmarkService.getBookmark(member.getMemberId()));
    }

    @DeleteMapping("/{bookmarkId}")
    @Operation(summary = "북마크 삭제")
    public ApiUtils.ApiResult<Boolean> deleteBookmark(@RequestHeader("Authorization") String header, @PathVariable String bookmarkId){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(bookmarkService.deleteBookmark(member, bookmarkId));
    }
}
