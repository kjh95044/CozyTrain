package com.ssafy.cozytrain.common.filter;

import com.ssafy.cozytrain.common.exception.TokenExpiredException;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.getHeaderToken(request, "Access");
        log.info(accessToken);
        String refreshToken = resolveRefreshToken(request);
        log.info(refreshToken);
        if(accessToken != null) {
            if(jwtUtil.tokenValidation(accessToken)){
                setAuthentication(jwtUtil.getIdFromToken(accessToken));
            }
            else if (refreshToken != null) {
                boolean isRefreshToken = jwtUtil.refreshTokenValidation(refreshToken);
                if (isRefreshToken) {
                    String loginId = jwtUtil.getIdFromToken(refreshToken);
                    String newAccessToken = jwtUtil.createToken(loginId, "Access");
                    jwtUtil.setHeaderAccessToken(response, newAccessToken);
                    setAuthentication(jwtUtil.getIdFromToken(newAccessToken));
                }
                else {
                    throw new TokenExpiredException("Refresh Token Expired");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
    public void setAuthentication(String memberId) {
        Authentication authentication = jwtUtil.createAuthentication(memberId);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
