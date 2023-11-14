package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCheckList is a Querydsl query type for CheckList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCheckList extends EntityPathBase<CheckList> {

    private static final long serialVersionUID = 1446184615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCheckList checkList1 = new QCheckList("checkList1");

    public final ListPath<CheckListItem, QCheckListItem> checkList = this.<CheckListItem, QCheckListItem>createList("checkList", CheckListItem.class, QCheckListItem.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> checkListDate = createDate("checkListDate", java.time.LocalDate.class);

    public final NumberPath<Long> checkListId = createNumber("checkListId", Long.class);

    public final QMember member;

    public final QReport report;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QCheckList(String variable) {
        this(CheckList.class, forVariable(variable), INITS);
    }

    public QCheckList(Path<? extends CheckList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCheckList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCheckList(PathMetadata metadata, PathInits inits) {
        this(CheckList.class, metadata, inits);
    }

    public QCheckList(Class<? extends CheckList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.report = inits.isInitialized("report") ? new QReport(forProperty("report"), inits.get("report")) : null;
    }

}

