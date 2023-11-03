package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.ChatRoom;
import com.ssafy.cozytrain.api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
