package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.entity.Member;

public interface DreamService {

    DreamDto.DreamDtoRes getDream(Long dreamId, Member member);

    DreamDto.DreamDtoListRes getDreams(Member member);

    DreamDto.DreamDtoRes saveDream(DreamDto.DreamDtoReq dreamDtoReq, Member member);

    boolean updateDream(Long dreamId, DreamDto.DreamDtoReq dreamDtoReq, Member member);

    boolean deleteDream(Long dreamId, Member member);
}
