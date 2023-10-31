package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class HealthDto {

    @Getter
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
}
