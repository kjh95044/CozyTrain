package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.api.dto.GuestBookDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.GuestBookService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/guest-book")
@RequiredArgsConstructor
public class GuestBookController {
    private final JwtUtils jwtUtils;
    private final GuestBookService guestBookService;
    private final MemberService memberService;

    @PostMapping
    @Operation(summary = "방명록 작성")
    public ApiUtils.ApiResult<Long> createGuestBook(@RequestHeader("Authorization") String header, @RequestBody GuestBookDto.GuestBookReqDto guestBookReqDto){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(guestBookService.createGuestBook(member, guestBookReqDto));
    }

    @GetMapping("/{countryId}")
    @Operation(summary = "방명록 나라별 랜덤 조회")
    public ApiUtils.ApiResult<GuestBookDto.GuestBookResDto> searchFriend(@RequestHeader("Authorization") String header, @PathVariable ("countryId") Long countryId){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(guestBookService.getRandomGuestBook(countryId));
    }
}
