package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Item;
import com.ssafy.cozytrain.api.entity.ItemList;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.querydsl.ItemListRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemListRepository extends JpaRepository<ItemList, Long>, ItemListRepositoryCustom {
    List<ItemList> findByMember(Member member);
    Optional<ItemList> findItemListByMemberAndItem(Member member, Item item);

}
