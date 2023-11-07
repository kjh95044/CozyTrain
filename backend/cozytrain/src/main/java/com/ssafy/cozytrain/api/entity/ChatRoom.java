package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class ChatRoom {

    @Id
    @Column(name = "chat_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @ManyToOne
    @JoinColumn(name = "member_first_id")
    private Member memberFirst;

    @ManyToOne
    @JoinColumn(name = "member_second_id")
    private Member memberSecond;

    @Builder
    public ChatRoom(Long chatRoomId, Member memberFirst, Member memberSecond) {
        this.chatRoomId = chatRoomId;
        this.memberFirst = memberFirst;
        this.memberSecond = memberSecond;
    }
}
