package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.GuestBookDto;
import com.ssafy.cozytrain.api.entity.Member;

public interface GuestBookService {
    Long createGuestBook(Member member, GuestBookDto.GuestBookReqDto guestBookReqDto);
    GuestBookDto.GuestBookResDto getRandomGuestBook(Member member, Long countryId);
}
