package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Item;
import com.ssafy.cozytrain.api.entity.ItemList;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ItemListRepository extends JpaRepository<ItemList, Long> {
    List<ItemList> findByMember(Member member);
    Optional<ItemList> findItemListByMemberAndItem(Member member, Item item);

}
