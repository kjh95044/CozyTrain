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
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    @Override
    public List<FriendDto.FriendSearchResDto> searchFriend(String memberId, String friendLoginId) {
        Member member = memberRepository.findByMemberLoginId(memberId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found User");
        });
        return friendRepository.searchFriend(member.getMemberId(), friendLoginId).orElseThrow(() -> {
            log.info("검색된 친구의 정보를 찾지 못했습니다.");
            return new NotFoundException("검색한 친구가 없습니다");
        });
    }

    @Override
    @Transactional
    public Long createFriend(String memberId, FriendDto.FriendReqDto friendReqDto) {
        Long friendId = friendReqDto.getMemberId();
        Member member = memberRepository.findByMemberLoginId(memberId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return  new NotFoundException("Not Found User");
        });

        int friendType = 0;
        Long memberFirstId = member.getMemberId();
        Long memberSecondId = friendId;

        if(member.getMemberId()>friendId){
            memberFirstId = friendId;
            memberSecondId = member.getMemberId();
            friendType = 1;
        }

        Member firstMember = memberRepository.findByMemberId(memberFirstId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found User");
        });
        Member secondMember = memberRepository.findByMemberId(memberSecondId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found User");
        });


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
        Friend friend = friendRepository.findById(friendAcceptReqDto.getFriendId()).orElseThrow(() -> {
            log.info("해당 친구요청에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found request Friend");
        });
        friend.updateFriendType(2);

        return friendRepository.save(friend).getFriendId();
    }

    @Override
    public Long deleteFriend(Long friendId) {
        return friendRepository.deleteByFriendId(friendId);
    }

    @Override
    @Transactional
    public List<FriendDto.FriendResDto> getFriendList(String memberId) {
        Member member = memberRepository.findByMemberLoginId(memberId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found User");
        });
        return friendRepository.getFriendList(member.getMemberId()).orElseThrow(() -> {
            log.info("친구 목록을 불러올 수 없습니다.");
            return new NotFoundException("Not Found Friend List");
        });
    }

    @Override
    @Transactional
    public List<FriendDto.FriendResDto> getSentRequestList(String memberId) {
        Member member = memberRepository.findByMemberLoginId(memberId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found User");
        });
        return friendRepository.getSentRequestList(member.getMemberId()).orElseThrow(() -> {
            log.info("보낸 친구 요청 목록을 찾지 못했습니다.");
            return new NotFoundException("Not Found Send Friend List");
        });
    }

    @Override
    @Transactional
    public List<FriendDto.FriendResDto> getReceivedRequestList(String memberId) {
        Member member = memberRepository.findByMemberLoginId(memberId).orElseThrow(() -> {
            log.info("해당 User에 대한 정보를 찾지 못했습니다.");
            return new NotFoundException("Not Found User");
        });
        return friendRepository.getReceivedRequestList(member.getMemberId()).orElseThrow(() -> {
            log.info("받은 친구 요청 목록을 찾지 못했습니다.");
            return new NotFoundException("Not Found Received Friend List");
        });
    }
}
