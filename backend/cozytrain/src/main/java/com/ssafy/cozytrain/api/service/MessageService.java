package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.MessageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MessageService {
    Long createMessage(String memberId, MultipartFile file, MessageDto.MessageReqDto messageReqDto) throws IOException;
}
