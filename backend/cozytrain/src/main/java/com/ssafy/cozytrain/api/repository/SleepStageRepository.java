package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Health;
import com.ssafy.cozytrain.api.entity.SleepStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepStageRepository extends JpaRepository<SleepStage, Long> {
//    List<SleepStage> findAllByHealthId(Long healthId);

    List<SleepStage> findAllByHealth(Health health);
}
