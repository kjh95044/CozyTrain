package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(timeToLive = 604800)
@Getter
@ToString
public class RefreshToken {

    @Id
    private String memberEmail;
    private String refreshToken;

    @Builder
    public RefreshToken(String memberEmail, String refreshToken) {
        this.memberEmail = memberEmail;
        this.refreshToken = refreshToken;
    }
}