package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.GuestBook;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

public class GuestBookDto {
    @Getter
    @NoArgsConstructor
    @ToString
    public static class GuestBookReqDto{
        private Long countryId;
        private String guestBookContent;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class GuestBookResDto{
        private Long GuestBookId;
        private Long countryId;
        private String guestBookContent;
        private LocalDate createdAt;
        private Long memberId;

        @Builder
        public GuestBookResDto(GuestBook guestBook){
            this.GuestBookId = guestBook.getGuestBookId();
            this.countryId = guestBook.getCountry().getCountryId();
            this.guestBookContent = guestBook.getGuestBookContent();
            this.createdAt = guestBook.getCreatedAt();
            this.memberId = guestBook.getMember().getMemberId();
        }
    }
}
