package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.CheckListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckListItemRepository extends JpaRepository<CheckListItem, Long> {
}
