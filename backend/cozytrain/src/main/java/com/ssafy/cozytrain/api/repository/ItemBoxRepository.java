package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Country;
import com.ssafy.cozytrain.api.entity.ItemBox;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemBoxRepository extends JpaRepository<ItemBox,Long> {
    List<ItemBox> findByMember(Member member);
    List<ItemBox> findItemBoxesByMemberAndCountry(Member member, Country country);
}
