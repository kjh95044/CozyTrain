package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final TrainService trainService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "레포트 생성")
    public ApiUtils.ApiResult<ReportDto.ReportDtoCommon> createReport(
            @RequestHeader("Authorization") String header,
            @RequestBody @Valid ReportDto.ReportDtoReq reportDtoReq) {
        log.info("health: " + reportDtoReq.getHealth().toString());
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        Long reportId = reportService.saveReport(reportDtoReq, member);

        var report = reportService.insertHealthScore(reportId);
        trainService.moveTrain(report.getSleepScore(),member);
        return success(report);
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
    public ApiUtils.ApiResult<ReportDto.ReportDtoByDate> getReportWeek(
            @RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(reportService.getOneWeekReports(member));
    }

    @GetMapping("between-dates")
    @Operation(summary = "레포트 기간별로 가져오기")
    public ApiUtils.ApiResult<ReportDto.ReportDtoByDate> getReportByDates(
            @RequestHeader("Authorization") String header,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(reportService.getReportsByDates(member, startDate, endDate));
    }

    @GetMapping("/{date}")
    @Operation(summary = "해당 날짜의 리포트 가져오기")
    public ApiUtils.ApiResult<ReportDto.ReportDtoCommon> getReportByDate(
            @RequestHeader("Authorization") String header,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        return success(reportService.getReportByDate(member, date));
    }
}
