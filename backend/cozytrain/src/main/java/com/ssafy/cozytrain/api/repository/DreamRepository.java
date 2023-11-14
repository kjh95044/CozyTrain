package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Dream;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DreamRepository extends JpaRepository<Dream, Long> {
    Dream findByDreamId(Long dreamId);

    List<Dream> findAllByMember(Member member);
}
