package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSleepStage is a Querydsl query type for SleepStage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSleepStage extends EntityPathBase<SleepStage> {

    private static final long serialVersionUID = 2022878630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSleepStage sleepStage = new QSleepStage("sleepStage");

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final QHealth health;

    public final NumberPath<Long> sleepStageId = createNumber("sleepStageId", Long.class);

    public final NumberPath<Integer> stage = createNumber("stage", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public QSleepStage(String variable) {
        this(SleepStage.class, forVariable(variable), INITS);
    }

    public QSleepStage(Path<? extends SleepStage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSleepStage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSleepStage(PathMetadata metadata, PathInits inits) {
        this(SleepStage.class, metadata, inits);
    }

    public QSleepStage(Class<? extends SleepStage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.health = inits.isInitialized("health") ? new QHealth(forProperty("health"), inits.get("health")) : null;
    }

}

