package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Long> {
}
