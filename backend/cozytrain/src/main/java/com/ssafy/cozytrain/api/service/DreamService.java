package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.DreamDto;

public interface DreamService {

    DreamDto.DreamDtoRes getDream(Long dreamId);

    DreamDto.DreamDtoListRes getDreams();

    boolean saveDream(DreamDto.DreamDtoReq dreamDtoReq);

    boolean updateDream(Long dreamId, DreamDto.DreamDtoReq dreamDtoReq);

    boolean deleteDream(Long dreamId);
}
