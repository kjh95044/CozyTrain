package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.dto.DreamDto.*;
import com.ssafy.cozytrain.api.entity.Dream;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DreamRepository extends JpaRepository<Dream, Long> {
//    DreamDtoRes findByMember(Member member);
    List<Dream> findAllByMember(Member member); // Select * from where member and date
}
