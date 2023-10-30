package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.dto.TokenDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.RefreshToken;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.api.repository.RefreshTokenRepository;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean signup(SignupReq signupReq) {
        if(memberRepository.findByMemberLoginId(signupReq.getMemberId()).isPresent()) {
            return false;
        }
        signupReq.update(passwordEncoder.encode(signupReq.getMemberPassword()));
        Member member = Member.builder().signupReq(signupReq).build();
        memberRepository.save(member);
        return true;
    }

    @Override
    public LoginRes login(LoginReq loginReq, HttpServletResponse response) {
        Member member = memberRepository.findByMemberLoginId(loginReq.memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        if(!passwordEncoder.matches(loginReq.getMemberPassword(), member.getMemberPassword())) {
            throw new NotFoundException("Not Found User");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReq.getMemberId());
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findById(loginReq.getMemberId());

        String cookieValue = null;
        RefreshToken newToken = new RefreshToken(loginReq.memberId, tokenDto.getRefreshToken());
        if(refreshToken.isPresent()) {
            cookieValue = refreshToken.get().getRefreshToken();
        }else {
            cookieValue = tokenDto.getRefreshToken();
        }
        refreshTokenRepository.save(newToken);
        setHeader(response, tokenDto);

        String cookieName = "refreshToken";
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");

        return LoginRes.builder()
                .member(member)
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(cookieValue).build();
    }

    @Override
    public Member findByMemberId(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException("Not Found User"));
        return member;
    }

    @Override
    public Optional<Member> findByMemberLoginId(String memberLoginId) {
        return memberRepository.findByMemberLoginId(memberLoginId);
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtils.ACCESS_TOKEN, tokenDto.getAccessToken());
    }
}
