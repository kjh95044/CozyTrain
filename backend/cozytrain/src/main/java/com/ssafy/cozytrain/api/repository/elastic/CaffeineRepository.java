package com.ssafy.cozytrain.api.repository.elastic;

import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaffeineRepository extends ElasticsearchRepository<CaffeineDocument, String> {
    Page<CaffeineDocument> findByNameOrMade(String name, String made, Pageable pageable);
}
