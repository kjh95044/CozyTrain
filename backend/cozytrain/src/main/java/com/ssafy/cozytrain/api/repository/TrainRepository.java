package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByMember(Member member);
}
