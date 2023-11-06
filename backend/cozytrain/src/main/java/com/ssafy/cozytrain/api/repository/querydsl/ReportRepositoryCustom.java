package com.ssafy.cozytrain.api.repository.querydsl;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepositoryCustom {
    Optional<ReportDto.ReportDtoRes> findReportInfo(Long reportId);
    List<Report> findReportsForLastWeek(Member member);
}
