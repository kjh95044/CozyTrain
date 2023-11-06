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


import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/train")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;
    @GetMapping("/cur-location-info")
    @Operation(summary = "현재 기차 위치 정보 가져오기")
    public ApiUtils.ApiResult<TrainDto.TrainCurInfoDto> getCurLocationInfo(
            @RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(trainService.getCurLocationInfo(member));
    }

    @GetMapping("/move")
    @Operation(summary = "기차를 점수만큼 이동 시킵니다.")
    public ApiUtils.ApiResult<TrainDto.TrainCurInfoDto> moveTrain(
            @RequestHeader("Authorization") String header, @RequestParam("sleepScore") int sleepScore) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        trainService.moveTrain(sleepScore, member);

        return success(trainService.getCurLocationInfo(member));
    }
}
