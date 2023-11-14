package com.ssafy.cozytrain.api.repository.querydsl;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.Health;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepositoryCustom {
    List<ReportDto.ReportDtoCommon> findReportsByMember(Member member);
    List<Report> findReportsForLastWeek(Member member);
    List<Report> findReportsByDate(Member member, LocalDate startDate, LocalDate endDate);
    ReportDto.ReportDtoCommon findReportInfoRecent(Member member);
    ReportDto.ReportDtoCommon findReportByDate(Member member, LocalDate date);
}
