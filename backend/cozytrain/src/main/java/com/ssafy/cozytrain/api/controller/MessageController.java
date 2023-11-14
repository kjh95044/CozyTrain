package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.MessageDto;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.MessageService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "음성메시지 전송 API")
    public ApiUtils.ApiResult<Long> createMessage(@RequestHeader("Authorization") String header, @RequestPart(value = "file") MultipartFile file, @RequestPart MessageDto.MessageReqDto messageReqDto) throws IOException {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(messageService.createMessage(memberId, file, messageReqDto));
    }

    @GetMapping("/{friendMemberId}")
    @Operation(summary = "1대1 음성메시지 전체 확인 API")
    public ApiUtils.ApiResult<List<MessageDto.MessageResDto>> getAllMessage(@RequestHeader("Authorization") String header, @PathVariable Long friendMemberId) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(messageService.getAllMessage(memberId, friendMemberId));
    }

    @DeleteMapping("/{messageId}")
    @Operation(summary = "음성메시지 삭제 API")
    public ApiUtils.ApiResult<Long> deleteMessage(@PathVariable Long messageId) {
        return success(messageService.deleteMessage(messageId));
    }
}
