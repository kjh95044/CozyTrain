package com.ssafy.cozytrain.api.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.api.entity.QFriend;
import com.ssafy.cozytrain.api.entity.QMember;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ssafy.cozytrain.api.entity.QFriend.friend;
import static com.ssafy.cozytrain.api.entity.QMember.member;

import static com.querydsl.core.group.GroupBy.groupBy;

@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<FriendDto.FriendSearchResDto>> searchFriend(Long memberId, String friendLoginId) {
        QFriend subFriend1 = new QFriend("subFriend1");
        QFriend subFriend2 = new QFriend("subFriend2");

        List<FriendDto.FriendSearchResDto> friendList = queryFactory
                .from(member)
                .where(member.memberId.notIn(JPAExpressions
                        .select(subFriend1.memberSecond.memberId)
                        .from(subFriend1)
                        .where(subFriend1.memberFirst.memberId.eq(memberId)))
                        .and(member.memberId.notIn(JPAExpressions
                                .select(subFriend2.memberFirst.memberId)
                                .from(subFriend2)
                                .where(subFriend2.memberSecond.memberId.eq(memberId))))
                        .and(member.memberId.ne(memberId))
                        .and(member.memberLoginId.contains(friendLoginId))
                )
                .orderBy(member.memberLoginId.desc())
                .transform(groupBy(member.memberId).list(Projections.constructor(FriendDto.FriendSearchResDto.class,
                        member.memberId, member.memberLoginId , member.memberName, member.memberImageUrl
                )));

        return Optional.ofNullable(friendList);
    }

    //    select *
//    from friend f
//    join member m
//    on f.member_first_id=m.member_id or f.member_second_id=m.member_id
//    where friend_type=2 and (f.member_first_id=3 or f.member_second_id=3)
//    group by m.member_id
//    having m.member_id != 3
//    order by f.updated_at desc;
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
                        friend.friendId, member.memberId, member.memberLoginId , member.memberName, member.memberImageUrl, friend.updatedAt
                        )));

        return Optional.ofNullable(friendList);
    }

    @Override
    public Optional<List<FriendDto.FriendResDto>> getSentRequestList(Long memberId) {
        List<FriendDto.FriendResDto> friendSendList1 = queryFactory
                .from(friend)
                .join(member).on(friend.memberSecond.memberId.eq(member.memberId))
                .where(friend.friendType.eq(0).and(friend.memberFirst.memberId.eq(memberId)))
                .orderBy(friend.updatedAt.desc())
                .transform(groupBy(member.memberId).list(Projections.constructor(FriendDto.FriendResDto.class,
                        friend.friendId, member.memberId, member.memberLoginId , member.memberName, member.memberImageUrl, friend.updatedAt
                )));

        List<FriendDto.FriendResDto> friendSendList2 = queryFactory
                .from(friend)
                .join(member).on(friend.memberFirst.memberId.eq(member.memberId))
                .where(friend.friendType.eq(1).and(friend.memberSecond.memberId.eq(memberId)))
                .orderBy(friend.updatedAt.desc())
                .transform(groupBy(member.memberId).list(Projections.constructor(FriendDto.FriendResDto.class,
                        friend.friendId, member.memberId, member.memberLoginId , member.memberName, member.memberImageUrl, friend.updatedAt
                )));

        List<FriendDto.FriendResDto> friendSendList = Stream.concat(friendSendList1.stream(), friendSendList2.stream())
                .collect(Collectors.toList());
        Collections.sort(friendSendList, new Comparator<FriendDto.FriendResDto>() {
            @Override
            public int compare(FriendDto.FriendResDto o1, FriendDto.FriendResDto o2) {
                return o2.getUpdatedAt().compareTo(o1.getUpdatedAt());
            }
        });

        return Optional.ofNullable(friendSendList);
    }

    @Override
    public Optional<List<FriendDto.FriendResDto>> getReceivedRequestList(Long memberId) {
        List<FriendDto.FriendResDto> friendReceivedList1 = queryFactory
                .from(friend)
                .join(member).on(friend.memberSecond.memberId.eq(member.memberId))
                .where(friend.friendType.eq(1).and(friend.memberFirst.memberId.eq(memberId)))
                .orderBy(friend.updatedAt.desc())
                .transform(groupBy(member.memberId).list(Projections.constructor(FriendDto.FriendResDto.class,
                        friend.friendId, member.memberId, member.memberLoginId , member.memberName, member.memberImageUrl, friend.updatedAt
                )));

        List<FriendDto.FriendResDto> friendReceivedList2 = queryFactory
                .from(friend)
                .join(member).on(friend.memberFirst.memberId.eq(member.memberId))
                .where(friend.friendType.eq(0).and(friend.memberSecond.memberId.eq(memberId)))
                .orderBy(friend.updatedAt.desc())
                .transform(groupBy(member.memberId).list(Projections.constructor(FriendDto.FriendResDto.class,
                        friend.friendId, member.memberId, member.memberLoginId , member.memberName, member.memberImageUrl, friend.updatedAt
                )));

        List<FriendDto.FriendResDto> friendReceivedList = Stream.concat(friendReceivedList1.stream(), friendReceivedList2.stream())
                .collect(Collectors.toList());
        Collections.sort(friendReceivedList, new Comparator<FriendDto.FriendResDto>() {
            @Override
            public int compare(FriendDto.FriendResDto o1, FriendDto.FriendResDto o2) {
                return o2.getUpdatedAt().compareTo(o1.getUpdatedAt());
            }
        });

        return Optional.ofNullable(friendReceivedList);
    }
}
