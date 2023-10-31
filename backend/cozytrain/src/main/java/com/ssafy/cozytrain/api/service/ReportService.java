package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.MemberDto;
import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;

public interface ReportService {
    Report saveReport(ReportDto.ReportDtoReq reportDtoReq, Member member);
}
