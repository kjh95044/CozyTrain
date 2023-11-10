package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDream is a Querydsl query type for Dream
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDream extends EntityPathBase<Dream> {

    private static final long serialVersionUID = -1582767772L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDream dream = new QDream("dream");

    public final StringPath dreamContent = createString("dreamContent");

    public final DatePath<java.time.LocalDate> dreamDate = createDate("dreamDate", java.time.LocalDate.class);

    public final NumberPath<Long> dreamId = createNumber("dreamId", Long.class);

    public final NumberPath<Integer> dreamType = createNumber("dreamType", Integer.class);

    public final QMember member;

    public QDream(String variable) {
        this(Dream.class, forVariable(variable), INITS);
    }

    public QDream(Path<? extends Dream> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDream(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDream(PathMetadata metadata, PathInits inits) {
        this(Dream.class, metadata, inits);
    }

    public QDream(Class<? extends Dream> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

