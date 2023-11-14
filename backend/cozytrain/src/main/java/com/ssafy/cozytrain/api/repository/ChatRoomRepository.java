package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByMemberFirst_MemberIdAndMemberSecond_MemberIdOrMemberSecond_MemberIdAndMemberFirst_MemberId
            (Long memberId1, Long memberId2, Long memberId3, Long memberId4);
}
