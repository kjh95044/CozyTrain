package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.ItemList;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemListRepository extends JpaRepository<ItemList, Long> {
    List<ItemList> findByMember(Member member);
}
