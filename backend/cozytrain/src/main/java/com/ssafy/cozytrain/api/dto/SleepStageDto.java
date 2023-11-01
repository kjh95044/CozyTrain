package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class SleepStageDto {
    @Getter
    public static class SleepStageDtoReq {
        private int stage;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        @Builder
        public SleepStageDtoReq(int stage, LocalDateTime startTime, LocalDateTime endTime){
            this.stage = stage;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    @Getter
    public static class SleepStageDtoRes {
        private int stage;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        @Builder
        public SleepStageDtoRes(int stage, LocalDateTime startTime, LocalDateTime endTime){
            this.stage = stage;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
