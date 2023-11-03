package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Train;

public interface TrainService {
    Long createTrain(Member member);
    TrainDto.TrainCurInfoDto getCurLocationInfo(Member member);
}
