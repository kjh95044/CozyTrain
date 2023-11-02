package com.ssafy.cozytrain.api.service.elastic;


import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import com.ssafy.cozytrain.api.entity.elastic.MediaInfoDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CaffeineService {
    Page<CaffeineDocument> searchCaffeine(String searchName, Pageable pageable);

}
