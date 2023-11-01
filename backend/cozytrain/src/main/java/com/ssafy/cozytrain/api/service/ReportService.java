package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.Member;

public interface ReportService {
    Long saveReport(ReportDto.ReportDtoReq reportDtoReq, Member member);
    ReportDto.ReportDtoRes getAnalyzedReport(Long reportId);
}
