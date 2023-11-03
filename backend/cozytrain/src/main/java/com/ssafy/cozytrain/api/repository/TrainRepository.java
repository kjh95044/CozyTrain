package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}
