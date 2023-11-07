package com.ssafy.cozytrain.api.entity.elastic;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "logstash-member")
public class MemberCompleteDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "member_id", analyzer = "suggest_index_analyzer", searchAnalyzer = "suggest_search_analyzer")
    private String memberId;

    @Field(type = FieldType.Text, name = "member_name", analyzer = "suggest_index_analyzer", searchAnalyzer = "suggest_search_analyzer")
    private String memberName;

    @Field(type = FieldType.Text, name = "member_login_id", analyzer = "suggest_index_analyzer", searchAnalyzer = "suggest_search_analyzer")
    private String memberLoginId;

    @Field(type = FieldType.Text, name = "created_at")
    private String createdAt;

    @Field(type = FieldType.Text, name = "updated_at")
    private String updatedAt;

    @Field(type = FieldType.Text, name = "member_image_url")
    private String memberImageUrl;

    @Field(type = FieldType.Text, name = "member_image_name")
    private String memberImageName;
}
