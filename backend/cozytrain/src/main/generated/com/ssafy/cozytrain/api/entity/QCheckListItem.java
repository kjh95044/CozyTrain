package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCheckListItem is a Querydsl query type for CheckListItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCheckListItem extends EntityPathBase<CheckListItem> {

    private static final long serialVersionUID = 1653885530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCheckListItem checkListItem = new QCheckListItem("checkListItem");

    public final QCheckList checkList;

    public final NumberPath<Long> checkListItemId = createNumber("checkListItemId", Long.class);

    public final StringPath elsId = createString("elsId");

    public QCheckListItem(String variable) {
        this(CheckListItem.class, forVariable(variable), INITS);
    }

    public QCheckListItem(Path<? extends CheckListItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCheckListItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCheckListItem(PathMetadata metadata, PathInits inits) {
        this(CheckListItem.class, metadata, inits);
    }

    public QCheckListItem(Class<? extends CheckListItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.checkList = inits.isInitialized("checkList") ? new QCheckList(forProperty("checkList"), inits.get("checkList")) : null;
    }

}

