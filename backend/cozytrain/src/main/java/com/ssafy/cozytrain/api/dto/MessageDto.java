package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MessageDto {
    // 메세지 요청 DTO
    @Getter
    @NoArgsConstructor
    public static class MessageReqDto{
        private Long chatRoomId;

        @Builder
        public MessageReqDto(Long chatRoomId) {
            this.chatRoomId = chatRoomId;
        }
    }
}
