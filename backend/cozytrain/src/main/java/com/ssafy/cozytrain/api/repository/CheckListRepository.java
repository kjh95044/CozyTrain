package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    Optional<CheckList> findByMemberAndCheckListDate(Member member, LocalDate date);
    void deleteByCheckListId(Long checkListId);
    CheckList findByCheckListDate(LocalDate date);
}
