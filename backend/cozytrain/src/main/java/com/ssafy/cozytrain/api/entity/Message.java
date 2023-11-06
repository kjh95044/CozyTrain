package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "message_url")
    private String messageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_member_id")
    private Member senderMember;

    @Builder
    public Message(Long messageId, String messageUrl, LocalDateTime createdAt, ChatRoom chatRoom, Member senderMember) {
        this.messageId = messageId;
        this.messageUrl = messageUrl;
        this.createdAt = createdAt;
        this.chatRoom = chatRoom;
        this.senderMember = senderMember;
    }
}
