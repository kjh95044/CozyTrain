package com.ssafy.cozytrain.common.filter;

import com.ssafy.cozytrain.common.exception.AccessTokenExpiredException;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.getHeaderToken(request, "Access");
        String refreshToken = jwtUtil.getHeaderToken(request, "Refresh");

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
                    throw new AccessTokenExpiredException("Access Token Expired");
                }
            }
        }
    }
    public void setAuthentication(String email) {
        Authentication authentication = jwtUtil.createAuthentication(email);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
