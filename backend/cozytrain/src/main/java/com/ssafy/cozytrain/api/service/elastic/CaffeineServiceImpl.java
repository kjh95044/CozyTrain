package com.ssafy.cozytrain.api.service.elastic;

import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import com.ssafy.cozytrain.api.repository.elastic.CaffeineRepository;
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

    @Override
    public Page<CaffeineDocument> searchCaffeine(String searchName, Pageable pageable) {
        return caffeineRepository.findByNameOrMade(searchName, searchName, pageable);
    }
}
