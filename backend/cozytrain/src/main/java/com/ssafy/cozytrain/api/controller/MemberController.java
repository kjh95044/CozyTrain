package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
}
