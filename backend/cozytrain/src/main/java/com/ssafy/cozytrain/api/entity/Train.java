package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@ToString
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
    @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    private Station station;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    private Track track;

    @Builder
    public Train(LocalDate startDate, LocalDateTime createdAt, Member member, Track track, Station station){
        this.startDate = startDate;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.member = member;
        this.track = track;
        this.station = station;
    }

    public void updateTrainCurDist(int trainCurDist){
        this.trainCurDist = trainCurDist;
        this.updatedAt = LocalDateTime.now();
    }
    public void updateTrainEndDate(LocalDate endDate){
        this.endDate = endDate;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateStation(Station station){
        this.station = station;
        this.updatedAt = LocalDateTime.now();
    }
}
