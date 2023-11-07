package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.dto.ItemBoxDto;
import com.ssafy.cozytrain.api.dto.ItemDto;
import com.ssafy.cozytrain.api.entity.Member;

import java.util.List;

public interface CollectionService {
    CollectionDto.CollectionDtoRes getCollection(Member member);
    List<ItemBoxDto.ItemBoxDtoRes> getMemberItemBoxes(Member member);
    ItemDto.ItemDtoRes getRandomItem(Long countryId, Member member);
}
