package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1575264935L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final ListPath<ChatRoom, QChatRoom> chatMemberFirst = this.<ChatRoom, QChatRoom>createList("chatMemberFirst", ChatRoom.class, QChatRoom.class, PathInits.DIRECT2);

    public final ListPath<ChatRoom, QChatRoom> chatMemberSecond = this.<ChatRoom, QChatRoom>createList("chatMemberSecond", ChatRoom.class, QChatRoom.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ListPath<Dream, QDream> dream = this.<Dream, QDream>createList("dream", Dream.class, QDream.class, PathInits.DIRECT2);

    public final ListPath<Friend, QFriend> friendMemberFirst = this.<Friend, QFriend>createList("friendMemberFirst", Friend.class, QFriend.class, PathInits.DIRECT2);

    public final ListPath<Friend, QFriend> friendMemberSecond = this.<Friend, QFriend>createList("friendMemberSecond", Friend.class, QFriend.class, PathInits.DIRECT2);

    public final QItemList item;

    public final ListPath<ItemBox, QItemBox> itemBoxes = this.<ItemBox, QItemBox>createList("itemBoxes", ItemBox.class, QItemBox.class, PathInits.DIRECT2);

    public final ListPath<ItemList, QItemList> itemLists = this.<ItemList, QItemList>createList("itemLists", ItemList.class, QItemList.class, PathInits.DIRECT2);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath memberImageName = createString("memberImageName");

    public final StringPath memberImageUrl = createString("memberImageUrl");

    public final StringPath memberLoginId = createString("memberLoginId");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberPassword = createString("memberPassword");

    public final ListPath<Message, QMessage> message = this.<Message, QMessage>createList("message", Message.class, QMessage.class, PathInits.DIRECT2);

    public final ListPath<Report, QReport> reports = this.<Report, QReport>createList("reports", Report.class, QReport.class, PathInits.DIRECT2);

    public final QTrain train;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItemList(forProperty("item"), inits.get("item")) : null;
        this.train = inits.isInitialized("train") ? new QTrain(forProperty("train"), inits.get("train")) : null;
    }

}

