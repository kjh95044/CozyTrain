package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemListRepository extends JpaRepository<ItemList, Long> {
}
