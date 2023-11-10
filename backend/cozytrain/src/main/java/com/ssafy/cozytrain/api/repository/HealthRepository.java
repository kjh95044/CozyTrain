package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Health;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthRepository extends JpaRepository<Health, Long> {
//    Optional<Health> findByReportId(Long reportId);
    Optional<Health> findByReport(Report report);
}
