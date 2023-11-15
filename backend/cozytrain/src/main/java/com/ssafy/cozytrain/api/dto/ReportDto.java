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
        private int moveDist;
        @Builder
        public ReportDtoCommon(LocalDate date, Health health, int caffeine, List<SleepStage> sleepStages, int moveDist){
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
            this.moveDist = moveDist;
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
            this.sleepScore = Math.round(sleepScore * 100)/100.0;
            this.sleepDuration = Math.round(sleepDuration * 100)/100.0;
            this.stressLevel = Math.round(stressLevel * 100)/100.0;
            this.steps = Math.round(steps * 100)/100.0;
            this.caffeine = Math.round(caffeine * 100)/100.0;
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
            this.averageSleep = Math.round(averageSleep * 100)/100.0;
            this.averageSleepScore = Math.round(averageSleepScore * 100)/100.0;;
            this.sleeps = sleeps;
            this.totalDate = totalDate;
        }
    }
}
