package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.DreamDto;

public interface DreamService {

    DreamDto.DreamDtoListRes getDreams();

    boolean saveDream(DreamDto.DreamDtoReq dreamDtoReq);

}
