package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.Report;
import com.ssafy.cozytrain.api.entity.SleepStage;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;


public class ReportDto {
    @Getter
    public static class ReportDtoReq{
        private HealthDto.HealthDtoReq health;
    }

    @Getter
    @ToString
    public static class ReportDtoRes{
        private int sleepScore;
        private List<SleepStageDto.SleepStageDtoRes> sleepStages;
        private int sleepDuration;
        private int stressLevel;
        private int steps;
        private int caffeine;

        @Builder
        public ReportDtoRes(int sleepScore, List<SleepStage> sleepStages, int sleepDuration, int steps, int stressLevel, int caffeine){
            this.sleepScore = sleepScore;
            this.sleepStages = sleepStages.stream().map(
                    sleepStage -> SleepStageDto.SleepStageDtoRes.builder()
                            .stage(sleepStage.getStage())
                            .startTime(sleepStage.getStartTime())
                            .endTime(sleepStage.getEndTime())
                            .build()
            ).collect(Collectors.toList());
            this.sleepDuration = sleepDuration;
            this.steps = steps;
            this.stressLevel = stressLevel;
            this.caffeine = caffeine;
        }
    }
}
