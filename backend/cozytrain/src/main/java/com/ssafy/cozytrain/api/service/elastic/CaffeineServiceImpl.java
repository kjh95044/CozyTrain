package com.ssafy.cozytrain.api.service.elastic;

import com.ssafy.cozytrain.api.entity.elastic.CaffeineCompleteDocument;
import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import com.ssafy.cozytrain.api.repository.elastic.CaffeineCompleteRepository;
import com.ssafy.cozytrain.api.repository.elastic.CaffeineRepository;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaffeineServiceImpl implements CaffeineService {

    private final CaffeineRepository caffeineRepository;
    private final CaffeineCompleteRepository caffeineCompleteRepository;

    @Override
    public Page<CaffeineDocument> searchCaffeine(String searchName, Pageable pageable) {
        return caffeineRepository.findByNameOrMade(searchName, searchName, pageable);
    }

    @Override
    public Page<CaffeineCompleteDocument> searchCaffeineComplete(String searchName, Pageable pageable) {
        return caffeineCompleteRepository.findByNameOrMade(searchName, searchName, pageable);
    }

    @Override
    public CaffeineDocument getCaffeineInfo(String id) {
        return caffeineRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Id"));
    }

}
