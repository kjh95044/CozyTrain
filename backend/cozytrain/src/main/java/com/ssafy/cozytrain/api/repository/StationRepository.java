package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
