package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.entity.Member;

public interface TrainService {
    Long createTrain(Member member);
}
