package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.ItemBox;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemBoxRepository extends JpaRepository<ItemBox,Long> {
    List<ItemBox> findByMember(Member member);
}
