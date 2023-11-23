package com.ssafy.cozytrain.api.repository.querydsl;

import com.ssafy.cozytrain.api.entity.ItemList;
import com.ssafy.cozytrain.api.entity.Member;

import java.util.List;

public interface ItemListRepositoryCustom {
    List<ItemList> findByMemberFetchJoin(Member member);
}
