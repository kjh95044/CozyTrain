package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;
import com.ssafy.cozytrain.api.repository.querydsl.ReportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {
    Optional<Report> findByMemberAndSleepReportDate(Member member, LocalDate date);
    Long deleteByReportId(Long reportId);
}

