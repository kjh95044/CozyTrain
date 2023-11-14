package com.ssafy.cozytrain.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.cozytrain.common.exception.AccessTokenExpiredException;
import com.ssafy.cozytrain.common.exception.RefreshTokenExpiredException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveAccessToken(request, AUTHORIZATION_HEADER);
        log.info(accessToken);

        String refreshToken = resolveRefreshToken(request);
        log.info("ref: " + refreshToken);

        if(accessToken != null) {
            if (jwtUtil.tokenValidation(accessToken)) {
                setAuthentication(jwtUtil.getIdFromToken(accessToken));
            }
            else if (refreshToken != null) {
                boolean isRefreshToken = jwtUtil.refreshTokenValidation(refreshToken);
                if (isRefreshToken) {
                    String loginId = jwtUtil.getIdFromToken(refreshToken);
                    String newAccessToken = jwtUtil.createToken(loginId, "Access_Token");

                    log.info("newAccessToken: " + newAccessToken);

                    jwtUtil.setHeaderAccessToken(response, newAccessToken);
                    setAuthentication(jwtUtil.getIdFromToken(newAccessToken));
                    setErrorResponse(HttpStatus.UNAUTHORIZED, response, newAccessToken);
                    return;
                }
                else {
                    setErrorResponse(HttpStatus.UNAUTHORIZED, response, "invalid refreshToken");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
        log.info("filter end");
    }
    public void setAuthentication(String memberId) {
        Authentication authentication = jwtUtil.createAuthentication(memberId);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("setAuth");
    }
    private String resolveAccessToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
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

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, String message) throws IOException {
        ApiUtils.ApiResult<?> errorResponse = ApiUtils.error(message, status);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
