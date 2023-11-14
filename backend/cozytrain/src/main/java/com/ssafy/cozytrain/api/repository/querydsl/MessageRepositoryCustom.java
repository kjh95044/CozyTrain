package com.ssafy.cozytrain.api.repository.querydsl;

import com.ssafy.cozytrain.api.dto.MessageDto;

import java.util.List;
import java.util.Optional;

public interface MessageRepositoryCustom {
    Optional<List<MessageDto.MessageResDto>> getAllMessage(Long chatRoomId);
}
