package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.api.entity.Friend;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.FriendRepository;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.api.service.FriendService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    @Override
    @Transactional
    public Long createFriend(Long memberId, FriendDto.FriendReqDto friendReqDto) {
        Long friendId = friendReqDto.getMemberId();

        int friendType = 0;
        Long memberFirstId = memberId;
        Long memberSecondId = friendId;

        if(memberId>friendId){
            memberFirstId = friendId;
            memberSecondId = memberId;
            friendType = 1;
        }

        Member firstMember = memberRepository.findByMemberId(memberFirstId).orElseThrow(() -> new NotFoundException("Not Found User"));
        Member secondMember = memberRepository.findByMemberId(memberSecondId).orElseThrow(() -> new NotFoundException("Not Found User"));


        Friend friend = Friend.builder()
                .memberFirst(firstMember)
                .memberSecond(secondMember)
                .friendType(friendType)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return friendRepository.save(friend).getFriendId();
    }

    @Override
    public Long acceptFriend(FriendDto.FriendAcceptReqDto friendAcceptReqDto) {
        Friend friend = friendRepository.findById(friendAcceptReqDto.getFriendId()).orElseThrow(() -> new NotFoundException("Not Found Friend"));
        friend.updateFriendType(2);

        return friendRepository.save(friend).getFriendId();
    }

    @Override
    public Long deleteFriend(Long friendId) {
        return friendRepository.deleteByFriendId(friendId);
    }
}
