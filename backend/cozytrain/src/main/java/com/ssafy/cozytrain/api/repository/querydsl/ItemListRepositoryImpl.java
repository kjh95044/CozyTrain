package com.ssafy.cozytrain.api.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cozytrain.api.entity.ItemList;
import com.ssafy.cozytrain.api.entity.Member;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.cozytrain.api.entity.QItem.item;
import static com.ssafy.cozytrain.api.entity.QItemList.itemList;
import static com.ssafy.cozytrain.api.entity.QMember.member;

@RequiredArgsConstructor
public class ItemListRepositoryImpl implements ItemListRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<ItemList> findByMemberFetchJoin(Member memberReq) {
        return queryFactory
                .select(itemList)
                .from(itemList)
                .leftJoin(itemList.members, member)
                .fetchJoin()
                .leftJoin(itemList.item, item)
                .fetchJoin()
                .distinct()
                .fetch();
    }
}
