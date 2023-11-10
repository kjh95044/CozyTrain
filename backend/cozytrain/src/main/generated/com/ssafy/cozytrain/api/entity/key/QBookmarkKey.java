package com.ssafy.cozytrain.api.entity.key;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookmarkKey is a Querydsl query type for BookmarkKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBookmarkKey extends BeanPath<BookmarkKey> {

    private static final long serialVersionUID = -61247397L;

    public static final QBookmarkKey bookmarkKey = new QBookmarkKey("bookmarkKey");

    public final StringPath elsId = createString("elsId");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QBookmarkKey(String variable) {
        super(BookmarkKey.class, forVariable(variable));
    }

    public QBookmarkKey(Path<? extends BookmarkKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookmarkKey(PathMetadata metadata) {
        super(BookmarkKey.class, metadata);
    }

}

