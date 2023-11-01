package com.ssafy.cozytrain.api.controller;


import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.api.service.FriendService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "친구 요청")
    public ApiUtils.ApiResult<Long> createFriend(/* @RequestHeader("Authorization") String header, */@RequestBody FriendDto.FriendReqDto friendReqDto){
        Long memberId = 3L;
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

}
