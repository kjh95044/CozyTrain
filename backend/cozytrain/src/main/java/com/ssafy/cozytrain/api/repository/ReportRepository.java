package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
