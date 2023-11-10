package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemList is a Querydsl query type for ItemList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemList extends EntityPathBase<ItemList> {

    private static final long serialVersionUID = -174391984L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemList itemList = new QItemList("itemList");

    public final QItem item;

    public final NumberPath<Long> itemListId = createNumber("itemListId", Long.class);

    public final QMember member;

    public final ListPath<Member, QMember> members = this.<Member, QMember>createList("members", Member.class, QMember.class, PathInits.DIRECT2);

    public QItemList(String variable) {
        this(ItemList.class, forVariable(variable), INITS);
    }

    public QItemList(Path<? extends ItemList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemList(PathMetadata metadata, PathInits inits) {
        this(ItemList.class, metadata, inits);
    }

    public QItemList(Class<? extends ItemList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

