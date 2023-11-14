package com.ssafy.cozytrain.api.entity.elastic;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "caffeine_search")
public class CaffeineDocument {

    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long seq;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String made;

    @Field(type = FieldType.Text)
    private String type;

    @Field(type = FieldType.Text)
    private String drinkType;

    @Field(type = FieldType.Long)
    private Long amount;

    @Field(type = FieldType.Double)
    private Double energy;

    @Field(type = FieldType.Double)
    private Double protein;

    @Field(type = FieldType.Double)
    private Double suger;
}
