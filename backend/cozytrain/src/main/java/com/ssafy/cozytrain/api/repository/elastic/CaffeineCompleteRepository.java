package com.ssafy.cozytrain.api.repository.elastic;

import com.ssafy.cozytrain.api.entity.elastic.CaffeineCompleteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaffeineCompleteRepository extends ElasticsearchRepository<CaffeineCompleteDocument, String> {
    Page<CaffeineCompleteDocument> findByNameOrMade(String name, String made, Pageable pageable);
}
