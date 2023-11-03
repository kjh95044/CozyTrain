package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    // 메세지 확인 응답 DTO
    @Getter
    @NoArgsConstructor
    public static class MessageResDto{
        private Long messageId;
        private String messageUrl;
        private LocalDateTime createdAt;
        private String senderLoginId;

        @Builder
        public MessageResDto(Long messageId, String messageUrl, LocalDateTime createdAt, String senderLoginId) {
            this.messageId = messageId;
            this.messageUrl = messageUrl;
            this.createdAt = createdAt;
            this.senderLoginId = senderLoginId;
        }
    }
}
