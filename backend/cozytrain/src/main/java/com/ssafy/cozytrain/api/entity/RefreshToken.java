package com.ssafy.cozytrain.api.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@ToString
@RedisHash(timeToLive = 604800)
public class RefreshToken {

    private String refreshToken;
    @Id
    private String memberId;

    public RefreshToken(String memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }
}