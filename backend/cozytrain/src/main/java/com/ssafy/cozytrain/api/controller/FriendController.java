package com.ssafy.cozytrain.api.controller;


import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.api.service.FriendService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final JwtUtils jwtUtils;

    @GetMapping("/search")
    @Operation(summary = "친구 검색")
    public ApiUtils.ApiResult<List<FriendDto.FriendSearchResDto>> searchFriend(@RequestHeader("Authorization") String header, @RequestParam("friendLoginId") String friendLoginId){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(friendService.searchFriend(memberId, friendLoginId));
    }

    @PostMapping
    @Operation(summary = "친구 요청")
    public ApiUtils.ApiResult<Long> createFriend(@RequestHeader("Authorization") String header, @RequestBody FriendDto.FriendReqDto friendReqDto){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(friendService.createFriend(memberId, friendReqDto));
    }

    @PatchMapping
    @Operation(summary = "친구 요청 수락")
    public ApiUtils.ApiResult<Long> acceptFriend(@RequestBody FriendDto.FriendAcceptReqDto friendAcceptReqDto){
        return success(friendService.acceptFriend(friendAcceptReqDto));
    }

    @DeleteMapping("/{friendId}")
    @Operation(summary = "친구 삭제")
    public ApiUtils.ApiResult<Long> deleteFriend(@PathVariable Long friendId){
        return success(friendService.deleteFriend(friendId));
    }

    @GetMapping()
    @Operation(summary = "친구 목록 불러오기")
    public ApiUtils.ApiResult<List<FriendDto.FriendResDto>> getFriendList(@RequestHeader("Authorization") String header){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(friendService.getFriendList(memberId));
    }

    @GetMapping("/send-list")
    @Operation(summary = "보낸 친구 요청 목록 불러오기")
    public ApiUtils.ApiResult<List<FriendDto.FriendResDto>> getSentRequestList(@RequestHeader("Authorization") String header){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(friendService.getSentRequestList(memberId));
    }

    @GetMapping("/received-list")
    @Operation(summary = "받은 친구 요청 목록 불러오기")
    public ApiUtils.ApiResult<List<FriendDto.FriendResDto>> getReceivedRequestList(@RequestHeader("Authorization") String header){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        return success(friendService.getReceivedRequestList(memberId));
    }

}
