package com.ssafy.cozytrain.api.repository.elastic;

import com.ssafy.cozytrain.api.entity.elastic.MediaInfoDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//@Repository
public interface MediaInfoRepository extends ElasticsearchRepository<MediaInfoDocument,String> {
    @Query("{\"bool\":{\"must\":[{\"match\":{\"titleNm.jaso\":{\"query\":\"?0\",\"analyzer\":\"suggest_search_analyzer\"}}}],\"should\":[{\"match\":{\"titleNm.ngram\":{\"query\":\"?0\",\"analyzer\":\"my_ngram_analyzer\"}}}]}}")
    List<MediaInfoDocument> findByTitleNm(String mediaType);
}
