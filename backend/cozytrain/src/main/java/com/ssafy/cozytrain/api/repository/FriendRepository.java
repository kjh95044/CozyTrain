package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Friend;
import com.ssafy.cozytrain.api.repository.querydsl.FriendRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>, FriendRepositoryCustom {

    Long deleteByFriendId(Long friendId);
}
