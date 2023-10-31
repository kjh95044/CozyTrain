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
        private String memberProfileImg;
        private String accessToken;
        private String refreshToken;

        @Builder
        public LoginRes(Member member, String accessToken, String refreshToken) {
            this.memberName = member.getMemberName();
            this.memberProfileImg = member.getMemberImageUrl();
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    public static class UpdateMemberReq {
        public String memberName;
        public MultipartFile memberImg;
    }
}
