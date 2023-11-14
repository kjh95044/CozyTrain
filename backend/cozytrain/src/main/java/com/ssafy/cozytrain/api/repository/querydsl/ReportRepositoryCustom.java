package com.ssafy.cozytrain.api.repository.querydsl;

import com.ssafy.cozytrain.api.dto.ReportDto;
import java.util.Optional;

public interface ReportRepositoryCustom {
    Optional<ReportDto.ReportDtoRes> findReportInfo(Long reportId);
}
