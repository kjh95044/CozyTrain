package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.Member;
import lombok.Builder;
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
//        private MultipartFile memberProfileImg;

        public void update(String memberPassword) {
            this.memberPassword = memberPassword;
        }
    }

    @Getter
    public static class LoginReq {
        @NotNull
        public String memberId;
        @NotNull
        public String memberPassword;
    }

    @Getter
    public static class LoginRes {
        private String memberName;
        private String memberAge;
        private String memberProfileImg;

        @Builder
        public LoginRes(Member member) {
            this.memberName = member.getMemberName();
            this.memberAge = member.getMemberAge();
            this.memberProfileImg = member.getMemberImageUrl();
        }
    }
}
