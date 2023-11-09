package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealth is a Querydsl query type for Health
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealth extends EntityPathBase<Health> {

    private static final long serialVersionUID = -1718758117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealth health = new QHealth("health");

    public final NumberPath<Long> healthId = createNumber("healthId", Long.class);

    public final QReport report;

    public final NumberPath<Integer> sleepDuration = createNumber("sleepDuration", Integer.class);

    public final NumberPath<Integer> sleepScore = createNumber("sleepScore", Integer.class);

    public final ListPath<SleepStage, QSleepStage> sleepStages = this.<SleepStage, QSleepStage>createList("sleepStages", SleepStage.class, QSleepStage.class, PathInits.DIRECT2);

    public final NumberPath<Integer> steps = createNumber("steps", Integer.class);

    public final NumberPath<Integer> stressLevel = createNumber("stressLevel", Integer.class);

    public QHealth(String variable) {
        this(Health.class, forVariable(variable), INITS);
    }

    public QHealth(Path<? extends Health> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealth(PathMetadata metadata, PathInits inits) {
        this(Health.class, metadata, inits);
    }

    public QHealth(Class<? extends Health> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.report = inits.isInitialized("report") ? new QReport(forProperty("report"), inits.get("report")) : null;
    }

}

