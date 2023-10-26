package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.entity.Member;

public interface DreamService {

    DreamDto.DreamDtoListRes getDreams();

    boolean saveDream(DreamDto.DreamDtoReq dreamDtoReq);

}
