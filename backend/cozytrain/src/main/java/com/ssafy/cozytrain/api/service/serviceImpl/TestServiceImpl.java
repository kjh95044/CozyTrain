package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.entity.Test;
import com.ssafy.cozytrain.api.repository.TestRepository;
import com.ssafy.cozytrain.api.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }
}
