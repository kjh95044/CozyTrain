package com.ssafy.cozytrain.common.utils;

import com.ssafy.cozytrain.api.dto.TokenDto;
import com.ssafy.cozytrain.api.entity.RefreshToken;
import com.ssafy.cozytrain.api.repository.RefreshTokenRepository;
import com.ssafy.cozytrain.api.service.serviceImpl.MemberDetailServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberDetailServiceImpl memberDetailService;

    private static final long ACCESS_TIME =  30 * 60 * 1000L;
    private static final long REFRESH_TIME = 14 * 24 * 60 * 60 * 1000L;
    public static final String ACCESS_TOKEN = "Access_Token";
    public static final String REFRESH_TOKEN = "Refresh_Token";


    @Value("${jwt.token.secret-key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String getHeaderToken(HttpServletRequest request, String type) {
        return request.getHeader(ACCESS_TOKEN);
    }

    public TokenDto createAllToken(String nickname) {
        return new TokenDto(createToken(nickname, "Access"), createToken(nickname, "Refresh"));
    }

    public String createToken(String nickname, String type) {
        Date date = new Date();
        long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;
        log.info(nickname);
        return Jwts.builder()
                .setSubject(nickname)
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public Boolean tokenValidation(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public Boolean refreshTokenValidation(String token) {
        if(!tokenValidation(token)) return false;

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findById(getIdFromToken(token));
        return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());
    }

    public Authentication createAuthentication(String userId) {
        UserDetails userDetails = memberDetailService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Access_Token", accessToken);
    }

}
