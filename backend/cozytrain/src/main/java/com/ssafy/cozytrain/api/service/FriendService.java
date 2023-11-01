package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.common.utils.ApiUtils;

public interface FriendService {

    Long createFriend(Long memberId, FriendDto.FriendReqDto friendReqDto);

    Long acceptFriend(FriendDto.FriendAcceptReqDto friendAcceptReqDto);

    Long deleteFriend(Long friendId);
}
