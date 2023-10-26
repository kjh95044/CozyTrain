package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.CheckListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckListItemRepository extends JpaRepository<CheckListItem, Long> {
    List<CheckListItem> findAllByCheckList(CheckList checkList);
}
