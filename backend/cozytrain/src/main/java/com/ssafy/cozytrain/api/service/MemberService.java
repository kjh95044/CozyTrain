package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.entity.Member;

import java.util.Optional;

public interface MemberService {
    Boolean signup(SignupReq signupReq);
    Member findByMemberId(Long memberId);
    Optional<Member> findByMemberLoginId(String memberLoginId);
}
