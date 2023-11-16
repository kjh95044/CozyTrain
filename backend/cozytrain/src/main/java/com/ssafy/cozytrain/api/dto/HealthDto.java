package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.CheckListItem;
import com.ssafy.cozytrain.api.entity.Report;
import com.ssafy.cozytrain.api.entity.SleepStage;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

public class HealthDto {

    @Getter
    @ToString
    public static class HealthDtoReq {
        private int stressLevel;
        private int sleepDuration;
        private int steps;
        private List<SleepStageDto.SleepStageDtoReq> sleepStages;

        @Builder
        public HealthDtoReq(int stressLevel, int sleepDuration, int steps){
            this.stressLevel = stressLevel;
            this.sleepDuration = sleepDuration;
            this.steps = steps;
        }
    }
    @Getter
    public static class HealthDtoRes {
        private int stressLevel;
        private int sleepDuration;
        private int steps;
        private int sleepScore;
        private List<SleepStageDto.SleepStageDtoRes> sleepStages;

        @Builder
        public HealthDtoRes(int stressLevel, int sleepDuration,int sleepScore, int steps, List<SleepStage> sleepStages){
            this.stressLevel = stressLevel;
            this.sleepDuration = sleepDuration;
            this.steps = steps;
            this.sleepScore = sleepScore;
            this.sleepStages = sleepStages.stream().map(
                    sleepStage -> SleepStageDto.SleepStageDtoRes.builder()
                            .stage(sleepStage.getStage())
                            .startTime(sleepStage.getStartTime())
                            .endTime(sleepStage.getEndTime())
                            .build()
            ).collect(Collectors.toList());
        }
    }
}
