package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Friend {
    @Id
    @Column(name = "friend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @NotNull
    @Column(name = "friend_type")
    private int friendType;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "member_first_id")
    private Member memberFirst;

    @ManyToOne
    @JoinColumn(name = "member_second_id")
    private Member memberSecond;

    @Builder
    public Friend(Long friendId, int friendType, LocalDateTime createdAt, LocalDateTime updatedAt, Member memberFirst, Member memberSecond) {
        this.friendId = friendId;
        this.friendType = friendType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberFirst = memberFirst;
        this.memberSecond = memberSecond;
    }

    public void updateFriendType(int friendType){
        this.friendType = friendType;
    }
    public void updateUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }
}
