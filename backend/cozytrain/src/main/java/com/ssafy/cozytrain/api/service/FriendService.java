package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.FriendDto;

import java.util.List;

public interface FriendService {

    Long createFriend(String memberId, FriendDto.FriendReqDto friendReqDto);

    Long acceptFriend(String memberId, FriendDto.FriendAcceptReqDto friendAcceptReqDto);

    Long deleteFriend(Long friendId);

    List<FriendDto.FriendResDto> getFriendList(String memberId);

    List<FriendDto.FriendResDto> getSentRequestList(String memberId);

    List<FriendDto.FriendResDto> getReceivedRequestList(String memberId);

    List<FriendDto.FriendSearchResDto> searchFriend(String memberId, String friendLoginId);
}
