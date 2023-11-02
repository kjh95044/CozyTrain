package com.ssafy.cozytrain.api.repository.querydsl;

import com.ssafy.cozytrain.api.dto.FriendDto;

import java.util.List;
import java.util.Optional;

public interface FriendRepositoryCustom {
    Optional<List<FriendDto.FriendSearchResDto>> searchFriend(Long memberId, String friendLoginId);
    Optional<List<FriendDto.FriendResDto>> getFriendList(Long memberId);
    Optional<List<FriendDto.FriendResDto>> getSentRequestList(Long memberId);
    Optional<List<FriendDto.FriendResDto>> getReceivedRequestList(Long memberId);

}
