package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.dto.TokenDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;

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
        response.addHeader(JwtUtils.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
