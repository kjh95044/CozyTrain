package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.MessageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MessageService {
    Long createMessage(String memberId, MultipartFile file, MessageDto.MessageReqDto messageReqDto) throws IOException;

    List<MessageDto.MessageResDto> getAllMessage(String memberId, Long chatRoomId);
}
