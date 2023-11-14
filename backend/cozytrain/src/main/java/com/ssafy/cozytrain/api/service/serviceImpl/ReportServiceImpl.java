package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.HealthDto;
import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.dto.SleepStageDto;
import com.ssafy.cozytrain.api.entity.*;
import com.ssafy.cozytrain.api.repository.HealthRepository;
import com.ssafy.cozytrain.api.repository.ReportRepository;
import com.ssafy.cozytrain.api.repository.SleepStageRepository;
import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final HealthRepository healthRepository;
    private final SleepStageRepository sleepStageRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveReport(ReportDto.ReportDtoReq reportDtoReq, Member member) {
        HealthDto.HealthDtoReq healthDtoReq = reportDtoReq.getHealth();
        List<SleepStageDto.SleepStageDtoReq> sleepStagesReq = healthDtoReq.getSleepStages();

        /*
            오늘의 report 가 이미 생성 되어 있으면 덮어 쓰기 한다.
         */

        Optional<Report> todayReport = existsReportToday(member);
        Report report;
        report = todayReport.orElseGet(() -> Report.builder()
                .member(member)
                .sleepReportDate(LocalDate.now())
                .build());

        report.updateUpdatedAt(LocalDateTime.now());
        Long reportId = reportRepository.save(report).getReportId();

        Health health;
        if(todayReport.isPresent()){
            health = healthRepository.findByReport(report).orElseThrow(() -> new NotFoundException("Not Found Health"));
            health.updateHealthData(healthDtoReq);
        }else {
            health = Health.builder()
                    .sleepDuration(healthDtoReq.getSleepDuration())
                    .stressLevel(healthDtoReq.getStressLevel())
                    .steps(healthDtoReq.getSteps())
                    .report(report)
                    .build();
        }

        healthRepository.save(health);

//        List<SleepStage> sleepStages;
//        if(todayReport.isPresent()){
//            sleepStages = sleepStageRepository.findAllByHealth(health);
//            for(SleepStage sleepStage : sleepStages){
//                sleepStage.updateSleepStage();
//            }
//        }
//        else{
//
//        }

        // TODO: 2023-11-02 수면 정보는 현재 이미 만들어져 있으면 덮어쓰기 안되게 되어 있다.

        for (SleepStageDto.SleepStageDtoReq sleepStageDtoReq : sleepStagesReq) {
            SleepStage sleepStage;
            if(todayReport.isPresent()){

            }
            else {
                sleepStage = SleepStage.builder()
                        .stage(sleepStageDtoReq.getStage())
                        .startTime(sleepStageDtoReq.getStartTime())
                        .endTime(sleepStageDtoReq.getEndTime())
                        .health(health)
                        .build();
                sleepStageRepository.save(sleepStage);
            }
        }

        return reportId;
    }

    @Override
    public ReportDto.ReportDtoRes getAnalyzedReport(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("Not Found Report"));

        Health health = healthRepository.findByReport(report)
                .orElseThrow(() -> new NotFoundException("Not Found Health"));

        List<SleepStage> sleepStages = sleepStageRepository.findAllByHealth(health);

        // TODO: 카페인 가져오는거 추가되면 수정 필요
        int caffeine = 100;

        // TODO: 2023-11-02 우선 querydsl 없이 해보고, 이후에 수정 하자.

        health.insertSleepScore(calculateSleepScore(sleepStages, health.getSleepDuration()));

        healthRepository.save(health);

        ReportDto.ReportDtoRes rp = ReportDto.ReportDtoRes.builder()
                .caffeine(caffeine)
                .sleepDuration(health.getSleepDuration())
                .steps(health.getSteps())
                .sleepScore(health.getSleepScore())
                .stressLevel(health.getStressLevel())
                .sleepStages(sleepStages)
                .build();

        log.info(rp.toString());

        return rp;
    }

    @Override
    public Optional<Report> existsReportToday(Member member) {
        return reportRepository.findByMemberAndSleepReportDate(member, LocalDate.now());
    }

    /*
        DEEP(5), LIGHT(4), SLEEPING(2), REM(6)에 가중치 주고, 나누기 전체 수면 시간 * 100
        (DEEP + REM + SLEEPING * 0.8 + LIGHT * 0.7) / 전체 수면 시간 * 100
     */
    private int calculateSleepScore(List<SleepStage> sleepStages, int sleepDuration){
        int score;
        if(sleepDuration < 450){
            score = (int) (4.4 * sleepDuration / 100);
        }else {
            score = 20;
        }


        for(var sleepStage : sleepStages){
            int stage = sleepStage.getStage();
            int minutes = (int) Duration.between(sleepStage.getStartTime(),sleepStage.getEndTime()).toMinutes();


            if(stage == StageType.DEEP.value){
                score += minutes;
            } else if (stage == StageType.REM.value) {
                score += minutes;
            } else if (stage == StageType.SLEEPING.value) {
                score += minutes * 0.8;
            } else if (stage == StageType.LIGHT.value) {
                score += minutes * 0.7;
            }
        }

        score = (score * 100) / sleepDuration ;
        if(score < 40)
            score = 40;
        else if (score>100) {
            score = 100;
        }

        return score;
    }

    public enum StageType {
        SLEEPING(2),
        LIGHT(4),
        DEEP(5),
        REM(6);
        private final int value;
        StageType(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
