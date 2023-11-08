package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    public static class LogoutReq {
        @NotNull
        public String memberId;
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
    public static class RefreshTokenRes {
        private String accessToken;

        @Builder
        public RefreshTokenRes(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Getter
    public static class UpdateMemberRes {
        public String memberImgUrl;

        @Builder
        public UpdateMemberRes(String memberImgUrl) {
            this.memberImgUrl = memberImgUrl;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateMemberReq {
        public String memberName;

        @Builder
        public UpdateMemberReq(String memberName) {
            this.memberName = memberName;
        }
    }
}
