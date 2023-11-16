package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.dto.SleepStageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class SleepStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sleepStageId;

    @NotNull
    private int stage;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_id", referencedColumnName = "health_id")
    private Health health;

    @Builder
    public SleepStage(int stage, LocalDateTime startTime, LocalDateTime endTime, Health health){
        this.stage = stage;
        this.startTime = startTime;
        this. endTime = endTime;
        this.health = health;
    }

    @Builder
    public SleepStage(int stage, LocalDateTime startTime, LocalDateTime endTime){
        this.stage = stage;
        this.startTime = startTime;
        this. endTime = endTime;
    }

    public void updateSleepStage(SleepStageDto.SleepStageDtoReq sleepStage){
        this.stage = sleepStage.getStage();
        this.startTime = sleepStage.getStartTime();
        this.endTime = sleepStage.getEndTime();
    }
}
