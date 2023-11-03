package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Long trainId;
    private int trainCurDist;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    private Track track;

    @Builder
    public Train(LocalDate startDate, LocalDateTime createdAt, Member member, Track track){
        this.startDate = startDate;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.member = member;
        this.track = track;
    }

    public void updateTrainCurDist(int trainCurDist){this.trainCurDist = trainCurDist;}
    public void updateTrainEndDate(LocalDate endDate){this.endDate = endDate;}
}
