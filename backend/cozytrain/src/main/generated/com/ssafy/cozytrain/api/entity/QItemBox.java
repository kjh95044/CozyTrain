package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemBox is a Querydsl query type for ItemBox
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemBox extends EntityPathBase<ItemBox> {

    private static final long serialVersionUID = -421276967L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemBox itemBox = new QItemBox("itemBox");

    public final QCountry country;

    public final NumberPath<Long> itemBoxId = createNumber("itemBoxId", Long.class);

    public final QMember member;

    public QItemBox(String variable) {
        this(ItemBox.class, forVariable(variable), INITS);
    }

    public QItemBox(Path<? extends ItemBox> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemBox(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemBox(PathMetadata metadata, PathInits inits) {
        this(ItemBox.class, metadata, inits);
    }

    public QItemBox(Class<? extends ItemBox> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

