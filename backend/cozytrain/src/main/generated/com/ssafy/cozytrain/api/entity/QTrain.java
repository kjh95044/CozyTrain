package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrain is a Querydsl query type for Train
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrain extends EntityPathBase<Train> {

    private static final long serialVersionUID = -1567995031L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrain train = new QTrain("train");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final QMember member;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final QStation station;

    public final QTrack track;

    public final NumberPath<Integer> trainCurDist = createNumber("trainCurDist", Integer.class);

    public final NumberPath<Long> trainId = createNumber("trainId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QTrain(String variable) {
        this(Train.class, forVariable(variable), INITS);
    }

    public QTrain(Path<? extends Train> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrain(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrain(PathMetadata metadata, PathInits inits) {
        this(Train.class, metadata, inits);
    }

    public QTrain(Class<? extends Train> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.station = inits.isInitialized("station") ? new QStation(forProperty("station"), inits.get("station")) : null;
        this.track = inits.isInitialized("track") ? new QTrack(forProperty("track")) : null;
    }

}

