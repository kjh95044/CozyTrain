package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.SleepStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepStageRepository extends JpaRepository<SleepStage, Long> {
}
