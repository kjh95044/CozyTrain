package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    Long saveReport(ReportDto.ReportDtoReq reportDtoReq, Member member);
    ReportDto.ReportDtoRes insertHealthScore(Long reportId);
    Optional<Report> findReportToday(Member member);
    ReportDto.ReportDtoRes getTodayReport(Member member);
    ReportDto.OneWeekReportDto getOneWeekReports(Member member);
}