package com.ssafy.cozytrain.api.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cozytrain.api.dto.FriendDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.ssafy.cozytrain.api.entity.QFriend.friend;
import static com.ssafy.cozytrain.api.entity.QMember.member;

import static com.querydsl.core.group.GroupBy.groupBy;

@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<FriendDto.FriendResDto>> getFriendList(Long memberId) {
        List<FriendDto.FriendResDto> friendList = queryFactory
                .from(friend)
                .join(member).on(friend.memberFirst.memberId.eq(member.memberId).or(friend.memberSecond.memberId.eq(member.memberId)))
                .where(friend.friendType.eq(2).and(friend.memberFirst.memberId.eq(memberId).or(friend.memberSecond.memberId.eq(memberId))))
                .groupBy(member.memberId)
                .having(member.memberId.ne(memberId))
                .orderBy(friend.updatedAt.desc())
                .transform(groupBy(member.memberId).list(Projections.constructor(FriendDto.FriendResDto.class,
                        friend.friendId, member.memberId, member.memberName, member.memberImageUrl
                        )));

        return Optional.ofNullable(friendList);
    }
}
