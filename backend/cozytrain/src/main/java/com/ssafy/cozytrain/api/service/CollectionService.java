package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.entity.Member;

public interface CollectionService {

    CollectionDto.CollectionDtoRes getCollection(Member member);
}
