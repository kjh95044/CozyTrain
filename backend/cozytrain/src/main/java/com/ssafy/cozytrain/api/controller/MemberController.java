package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API")
    public ApiUtils.ApiResult<Boolean> signUp(@RequestBody @Valid SignupReq signupReq) {
        return success(memberService.signup(signupReq));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    public ApiUtils.ApiResult<LoginRes> login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse response) {
        return success(memberService.login(loginReq, response));
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 API")
    public ApiUtils.ApiResult<Integer> logout(HttpServletRequest request, @RequestBody @Valid LogoutReq logoutReq) {
        return success(memberService.logout(request, logoutReq));
    }

    @PatchMapping("/image")
    @Operation(summary = "회원 사진 수정 API")
    public ApiUtils.ApiResult<UpdateMemberRes> updateImage(@RequestHeader("Authorization") String header, @RequestParam(name = "image") MultipartFile file) throws IOException {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(memberService.updateMemberImg(file, member));
    }

    @PatchMapping("/name")
    @Operation(summary = "회원 이름 수정 API")
    public ApiUtils.ApiResult<Boolean> updateName(@RequestHeader("Authorization") String header, @RequestBody UpdateMemberReq updateMemberReq) throws IOException {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(memberService.updateMemberName(updateMemberReq, member));
    }

    @DeleteMapping
    @Operation(summary = "회원 탈퇴 API")
    public ApiUtils.ApiResult<Boolean> deleteMember(@RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(memberService.deleteMember(member));
    }

    @GetMapping("/{loginId}")
    @Operation(summary = "회원 아이디 중복 체크 API", description = "header에 token 필요 X")
    public ApiUtils.ApiResult<Boolean> findMemberId(@PathVariable String loginId) {
        return success(memberService.findMemberLoginId(loginId));
    }
}
