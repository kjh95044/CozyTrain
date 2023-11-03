package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/train")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;
//    @PostMapping
//    @Operation(summary = "기차 생성")
//    public ApiUtils.ApiResult<Long> createTrain(
//            @RequestHeader("Authorization") String header,
//            @RequestBody @Valid TrainDto.TrainDtoReq trainDtoReq) {
//        String memberId = jwtUtils.getIdFromToken(header.substring(7));
//        Member member = memberService.findByMemberLoginId(memberId)
//                .orElseThrow(() -> new NotFoundException("Not Found User"));
//
//        return success(trainService.createTrain(trainDtoReq, member));
//    }
}
