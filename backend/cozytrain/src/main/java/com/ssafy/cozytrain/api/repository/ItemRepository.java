package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
