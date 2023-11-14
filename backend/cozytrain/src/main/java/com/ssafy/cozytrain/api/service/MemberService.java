package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

public interface MemberService {
    Boolean signup(SignupReq signupReq);
    LoginRes login(LoginReq loginReq, HttpServletResponse response);
    Optional<Member> findByMemberLoginId(String memberLoginId);
    Boolean findMemberLoginId(String memberLoginId);
    UpdateMemberRes updateMemberImg(MultipartFile file, Member member) throws IOException;
    Boolean updateMemberName(UpdateMemberReq updateMemberReq, Member member);
    Boolean deleteMember(Member member);
    Integer logout(HttpServletRequest request, LogoutReq logoutReq);
}
