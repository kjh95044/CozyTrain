package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStation is a Querydsl query type for Station
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStation extends EntityPathBase<Station> {

    private static final long serialVersionUID = -139622827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStation station = new QStation("station");

    public final StringPath continent = createString("continent");

    public final QCountry country;

    public final NumberPath<Integer> dist = createNumber("dist", Integer.class);

    public final StringPath region = createString("region");

    public final NumberPath<Integer> regionNum = createNumber("regionNum", Integer.class);

    public final NumberPath<Long> stationId = createNumber("stationId", Long.class);

    public final QTrack track;

    public final ListPath<Train, QTrain> trains = this.<Train, QTrain>createList("trains", Train.class, QTrain.class, PathInits.DIRECT2);

    public QStation(String variable) {
        this(Station.class, forVariable(variable), INITS);
    }

    public QStation(Path<? extends Station> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStation(PathMetadata metadata, PathInits inits) {
        this(Station.class, metadata, inits);
    }

    public QStation(Class<? extends Station> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.track = inits.isInitialized("track") ? new QTrack(forProperty("track")) : null;
    }

}

