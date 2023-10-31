package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.entity.Member;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface MemberService {
    Boolean signup(SignupReq signupReq);
    LoginRes login(LoginReq loginReq, HttpServletResponse response);
    Member findByMemberId(Long memberId);
    Optional<Member> findByMemberLoginId(String memberLoginId);
    Boolean updateMemberImg(UpdateMemberReq updateMemberReq, Member member) throws IOException;
}
