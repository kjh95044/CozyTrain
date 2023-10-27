package com.ssafy.cozytrain.api.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class MemberDto {

    @Getter
    public static class SignupReq {
        @NotNull
        private String memberId;
        @NotNull
        private String memberPassword;
        @NotNull
        private String memberName;
        @NotNull
        private String memberAge;
        private MultipartFile memberProfileImg;

        public void update(String memberPassword) {
            this.memberPassword = memberPassword;
        }
    }
}
