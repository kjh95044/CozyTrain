package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member findByMemberId(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException("Not Found User"));
        return member;
    }
}
