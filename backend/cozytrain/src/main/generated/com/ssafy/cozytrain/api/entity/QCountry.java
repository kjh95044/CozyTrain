package com.ssafy.cozytrain.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCountry is a Querydsl query type for Country
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCountry extends EntityPathBase<Country> {

    private static final long serialVersionUID = -1579623241L;

    public static final QCountry country = new QCountry("country");

    public final NumberPath<Long> countryId = createNumber("countryId", Long.class);

    public final StringPath countryName = createString("countryName");

    public final ListPath<ItemBox, QItemBox> itemBoxes = this.<ItemBox, QItemBox>createList("itemBoxes", ItemBox.class, QItemBox.class, PathInits.DIRECT2);

    public final ListPath<Item, QItem> items = this.<Item, QItem>createList("items", Item.class, QItem.class, PathInits.DIRECT2);

    public final ListPath<Station, QStation> stations = this.<Station, QStation>createList("stations", Station.class, QStation.class, PathInits.DIRECT2);

    public QCountry(String variable) {
        super(Country.class, forVariable(variable));
    }

    public QCountry(Path<? extends Country> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountry(PathMetadata metadata) {
        super(Country.class, metadata);
    }

}

