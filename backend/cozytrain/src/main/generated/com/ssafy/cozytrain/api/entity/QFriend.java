package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFriend is a Querydsl query type for Friend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriend extends EntityPathBase<Friend> {

    private static final long serialVersionUID = -1763779235L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFriend friend = new QFriend("friend");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> friendId = createNumber("friendId", Long.class);

    public final NumberPath<Integer> friendType = createNumber("friendType", Integer.class);

    public final QMember memberFirst;

    public final QMember memberSecond;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QFriend(String variable) {
        this(Friend.class, forVariable(variable), INITS);
    }

    public QFriend(Path<? extends Friend> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFriend(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFriend(PathMetadata metadata, PathInits inits) {
        this(Friend.class, metadata, inits);
    }

    public QFriend(Class<? extends Friend> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberFirst = inits.isInitialized("memberFirst") ? new QMember(forProperty("memberFirst"), inits.get("memberFirst")) : null;
        this.memberSecond = inits.isInitialized("memberSecond") ? new QMember(forProperty("memberSecond"), inits.get("memberSecond")) : null;
    }

}

