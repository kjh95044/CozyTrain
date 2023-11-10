package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.Health;
import com.ssafy.cozytrain.api.entity.Report;
import com.ssafy.cozytrain.api.entity.SleepStage;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class ReportDto {
    @Getter
    public static class ReportDtoReq{
        private HealthDto.HealthDtoReq health;
    }

    @Getter
    @ToString
    public static class ReportDtoCommon{
        private LocalDate date;
        private int sleepScore;
        private List<SleepStageDto.SleepStageDtoRes> sleepStages;
        private int sleepDuration;
        private int stressLevel;
        private int steps;
        private int caffeine;
        @Builder
        public ReportDtoCommon(LocalDate date, Health health, int caffeine, List<SleepStage> sleepStages){
            this.date = date;
            this.sleepScore = health.getSleepScore();
            this.sleepStages = sleepStages.stream().map(
                    sleepStage -> SleepStageDto.SleepStageDtoRes.builder()
                            .stage(sleepStage.getStage())
                            .startTime(sleepStage.getStartTime())
                            .endTime(sleepStage.getEndTime())
                            .build()
            ).collect(Collectors.toList());
            this.sleepDuration = health.getSleepDuration();
            this.steps = health.getSteps();
            this.stressLevel = health.getStressLevel();
            this.caffeine = caffeine;
        }
        @Builder
        public ReportDtoCommon(LocalDate date, int sleepScore, List<SleepStage> sleepStages, int sleepDuration, int stressLevel, int steps, int caffeine){
            this.date = date;
            this.sleepScore = sleepScore;
            this.sleepStages = sleepStages.stream().map(
                    sleepStage -> SleepStageDto.SleepStageDtoRes.builder()
                            .stage(sleepStage.getStage())
                            .startTime(sleepStage.getStartTime())
                            .endTime(sleepStage.getEndTime())
                            .build()
            ).collect(Collectors.toList());
            this.sleepDuration = sleepDuration;
            this.steps = stressLevel;
            this.stressLevel = steps;
            this.caffeine = caffeine;
        }
    }

    @Getter
    public static class ReportDtoAverage{
        private double sleepScore;
        private double sleepDuration;
        private double stressLevel;
        private double steps;
        private double caffeine;

        @Builder
        public ReportDtoAverage(double sleepScore, double sleepDuration, double stressLevel, double steps, double caffeine){
            this.sleepScore = sleepScore;
            this.sleepDuration = sleepDuration;
            this.stressLevel = stressLevel;
            this.steps = steps;
            this.caffeine = caffeine;
        }
    }
    @Getter
    @ToString
    public static class ReportDtoRes{
        private ReportDtoCommon todayReport;
        private ReportDtoCommon recentReport;
        private ReportDtoAverage averageReport;

        @Builder
        public ReportDtoRes(ReportDtoCommon todayReport, ReportDtoCommon recentReport, ReportDtoAverage averageReport){
            this.todayReport = todayReport;
            this.recentReport = recentReport;
            this.averageReport = averageReport;
        }
    }

    @Getter
    @ToString
    public static class ReportDtoByDate{
        private long totalDate;
        private double averageSleep;
        private double averageSleepScore;
        private List<SleepDto> sleeps;

        @Builder
        public ReportDtoByDate(double averageSleep, double averageSleepScore, List<SleepDto> sleeps, long totalDate){
            this.averageSleep = averageSleep;
            this.averageSleepScore = averageSleepScore;
            this.sleeps = sleeps;
            this.totalDate = totalDate;
        }
    }
}
