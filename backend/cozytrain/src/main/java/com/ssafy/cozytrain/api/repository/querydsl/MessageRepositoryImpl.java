package com.ssafy.cozytrain.api.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cozytrain.api.dto.MessageDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.ssafy.cozytrain.api.entity.QMember.member;
import static com.ssafy.cozytrain.api.entity.QMessage.message;

import static com.querydsl.core.group.GroupBy.groupBy;
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<MessageDto.MessageResDto>> getAllMessage(Long chatRoomId) {
        List<MessageDto.MessageResDto> messageList = queryFactory
                .from(message)
                .join(member).on(message.senderMember.memberId.eq(member.memberId))
                .where(message.chatRoom.chatRoomId.eq(chatRoomId))
                .orderBy(message.createdAt.desc())
                .transform(groupBy(message.messageId).list(Projections.constructor(MessageDto.MessageResDto.class,
                        message.messageId, message.messageUrl, message.createdAt, member.memberLoginId, message.isRead)));
        return Optional.ofNullable(messageList);
    }
}
