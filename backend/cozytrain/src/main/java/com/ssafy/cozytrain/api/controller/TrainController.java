package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.dto.VisitDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @GetMapping("/visit/continent")
    @Operation(summary = "6개의 대륙중에 어떤 대륙을 방문 했는지 반환")
    public ApiUtils.ApiResult<List<VisitDto.ContinentDto>> checkTrainVisitContinent(
            @RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(trainService.checkTrainVisitContinent(member));
    }

    @GetMapping("/visit/country")
    @Operation(summary = "해당 대륙에 방문한 국가들을 반환")
    @ApiImplicitParam(name = "continent", value = "{\"asia\", \"europe\", \"africa\", \"oceania\", \"north_america\", \"south_america\"} 이 배열중에 하나 넣으시오")
    public ApiUtils.ApiResult<List<VisitDto.CountryDto>> checkTrainVisitCountry(
            @RequestHeader("Authorization") String header, @RequestParam("continent")String continent) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(trainService.checkTrainVisitCountry(continent, member));
    }
}
