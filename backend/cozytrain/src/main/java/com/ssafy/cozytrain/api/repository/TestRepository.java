package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAll();
}
