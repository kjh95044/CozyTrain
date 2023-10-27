package com.ssafy.cozytrain.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberNickName;
    private String memberAgeRange;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CheckList> checkList;

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberNickName='" + memberNickName + '\'' +
                ", memberAgeRange='" + memberAgeRange + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
