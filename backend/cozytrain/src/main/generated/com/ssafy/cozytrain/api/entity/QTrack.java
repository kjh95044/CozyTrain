package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrack is a Querydsl query type for Track
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrack extends EntityPathBase<Track> {

    private static final long serialVersionUID = -1567995220L;

    public static final QTrack track = new QTrack("track");

    public final ListPath<Station, QStation> stations = this.<Station, QStation>createList("stations", Station.class, QStation.class, PathInits.DIRECT2);

    public final NumberPath<Long> trackId = createNumber("trackId", Long.class);

    public final ListPath<Train, QTrain> trains = this.<Train, QTrain>createList("trains", Train.class, QTrain.class, PathInits.DIRECT2);

    public QTrack(String variable) {
        super(Track.class, forVariable(variable));
    }

    public QTrack(Path<? extends Track> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrack(PathMetadata metadata) {
        super(Track.class, metadata);
    }

}

