package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.dto.MemberDto;
import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "레포트 생성")
    public ApiUtils.ApiResult<ReportDto.ReportDtoRes> createReport(
            @RequestHeader("Authorization") String header,
            @RequestBody @Valid ReportDto.ReportDtoReq reportDtoReq) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        Long reportId = reportService.saveReport(reportDtoReq, member);

        return success(reportService.insertHealthScore(reportId));
    }

    @GetMapping()
    @Operation(summary = "오늘 레포트 가져오기")
    public ApiUtils.ApiResult<ReportDto.ReportDtoRes> getReportToday(
            @RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(reportService.getTodayReport(member));
    }

    @GetMapping("week")
    @Operation(summary = "주간 레포트 가져오기")
    public ApiUtils.ApiResult<ReportDto.OneWeekReportDto> getReportWeek(
            @RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(reportService.getOneWeekReports(member));
    }
}
