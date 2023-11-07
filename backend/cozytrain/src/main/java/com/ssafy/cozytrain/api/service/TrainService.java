package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.dto.VisitDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Train;

import java.util.List;

public interface TrainService {
    Long createTrain(Member member);
    TrainDto.TrainCurInfoDto getCurLocationInfo(Member member);
    Train getTrain(Member member);
    void moveTrain(int sleepScore, Member member);
    List<VisitDto.ContinentDto> checkTrainVisitContinent(Member member);
    List<VisitDto.CountryDto> checkTrainVisitCountry(String continent, Member member);
}
