package com.ssafy.cozytrain.api.service.elastic;


import com.ssafy.cozytrain.api.entity.elastic.CaffeineCompleteDocument;
import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CaffeineService {
    Page<CaffeineDocument> searchCaffeine(String searchName, Pageable pageable);
    Page<CaffeineCompleteDocument> searchCaffeineComplete(String searchName, Pageable pageable);
    CaffeineDocument getCaffeineInfo(String id);
}
