package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportService {
    Long saveReport(ReportDto.ReportDtoReq reportDtoReq, Member member);
    ReportDto.ReportDtoRes getAnalyzedReport(Long reportId);
    Optional<Report> existsReportToday(Member member);
}