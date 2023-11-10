package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.*;
import com.ssafy.cozytrain.api.entity.*;
import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import com.ssafy.cozytrain.api.repository.HealthRepository;
import com.ssafy.cozytrain.api.repository.ReportRepository;
import com.ssafy.cozytrain.api.repository.SleepStageRepository;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.api.service.elastic.CaffeineService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final HealthRepository healthRepository;
    private final SleepStageRepository sleepStageRepository;
    private final CheckListService checkListService;
    private final CaffeineService caffeineService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveReport(ReportDto.ReportDtoReq reportDtoReq, Member member) {
        HealthDto.HealthDtoReq healthDtoReq = reportDtoReq.getHealth();
        List<SleepStageDto.SleepStageDtoReq> sleepStagesReq = healthDtoReq.getSleepStages();

        /*
            오늘의 report 가 이미 생성 되어 있으면 덮어 쓰기 한다.
         */

        Optional<Report> todayReport = findReportToday(member);
        Report report;
        int caffeine = getCaffeineTotal(member);

        report = todayReport.orElseGet(() -> Report.builder()
                .member(member)
                .caffeine(caffeine)
                .sleepReportDate(LocalDate.now())
                .build());

        report.updateUpdatedAt(LocalDateTime.now());
        Long reportId = reportRepository.save(report).getReportId();

        Health health;
        if (todayReport.isPresent()) {
            health = healthRepository.findByReport(report).orElseThrow(() -> new NotFoundException("Not Found Health"));
            health.updateHealthData(healthDtoReq);
        } else {
            health = Health.builder()
                    .sleepDuration(healthDtoReq.getSleepDuration())
                    .stressLevel(healthDtoReq.getStressLevel())
                    .steps(healthDtoReq.getSteps())
                    .report(report)
                    .build();
        }

        healthRepository.save(health);

        // TODO: 2023-11-02 수면 정보는 현재 이미 만들어져 있으면 덮어쓰기 안되게 되어 있다.

        for (SleepStageDto.SleepStageDtoReq sleepStageDtoReq : sleepStagesReq) {
            SleepStage sleepStage;
            if (todayReport.isPresent()) {

            } else {
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

    /*
        - 커피류, 카페 : 120mg
        - 커피류, 가공식품 : 50mg
        - 차류, 카페 OR 가공식품 : 20mg
        - 나머지 0mg
     */
    private int getCaffeineTotal(Member member) {
        CheckListDto.CheckListTodayRes checkListTodayRes = checkListService.checkListToday(member);

        if (checkListTodayRes == null) {
            return 0;
        }

        int caffeine = 0;
        for (CheckListDto.CheckListRes item : checkListTodayRes.getCheckListDtoList()) {
            CaffeineDocument caffeineDocument = caffeineService.getCaffeineInfo(item.getElsId());
            String drinkType = caffeineDocument.getDrinkType();
            String type = caffeineDocument.getType();

            switch (drinkType) {
                case "커피류":
                    if ("카페".equals(type)) {
                        caffeine += 120;
                    } else if ("가공식품".equals(type)) {
                        caffeine += 50;
                    }
                    break;
                case "차류":
                    if ("카페".equals(type) || "가공식품".equals(type)) {
                        caffeine += 20;
                    }
                    break;
            }
        }
        return caffeine;
    }

    @Override
    public ReportDto.ReportDtoCommon insertHealthScore(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("Not Found Report"));

        Health health = healthRepository.findByReport(report)
                .orElseThrow(() -> new NotFoundException("Not Found Health"));

        List<SleepStage> sleepStages = sleepStageRepository.findAllByHealth(health);

        health.insertSleepScore(calculateSleepScore(sleepStages, health.getSleepDuration()));

        healthRepository.save(health);

        ReportDto.ReportDtoCommon rp = ReportDto.ReportDtoCommon.builder()
                .date(report.getSleepReportDate())
                .caffeine(report.getCaffeine())
                .health(health)
                .sleepStages(sleepStages)
                .build();

        log.info(rp.toString());

        return rp;
    }

    @Override
    public Optional<Report> findReportToday(Member member) {
        return reportRepository.findByMemberAndSleepReportDate(member, LocalDate.now());
    }

    @Override
    public ReportDto.ReportDtoRes getTodayReport(Member member) {
        // 오늘 리포트 기존방식
//        Report todayReport = findReportToday(member)
//                .orElseThrow(() -> new NotFoundException("Not Found Report"));
//        Health todayHealth = healthRepository.findByReport(todayReport)
//                .orElseThrow(() -> new NotFoundException("Not Found Health"));
//        List<SleepStage> sleepStages = sleepStageRepository.findAllByHealth(todayHealth);
//
//        ReportDto.ReportDtoCommon reportCommonToday =
//                ReportDto.ReportDtoCommon.builder()
//                    .caffeine(todayReport.getCaffeine())
//                    .health(todayHealth)
//                    .sleepStages(sleepStages)
//                    .build();
        // 오늘 리포트 querydsl
        ReportDto.ReportDtoCommon reportCommonToday = reportRepository.findReportByDate(member, LocalDate.now());
        log.info(reportCommonToday.toString());

        // 평균 리포트
        List<ReportDto.ReportDtoCommon> reports = reportRepository.findReportsByMember(member);
        ReportDto.ReportDtoAverage averageReport = ReportDto.ReportDtoAverage.builder().build();

        if (!reports.isEmpty()) {
            double averageSleepScore = reports.stream().mapToDouble(ReportDto.ReportDtoCommon::getSleepScore).average().orElse(0);
            double averageSleepDuration = reports.stream().mapToDouble(ReportDto.ReportDtoCommon::getSleepDuration).average().orElse(0);
            double averageStressLevel = reports.stream().mapToDouble(ReportDto.ReportDtoCommon::getStressLevel).average().orElse(0);
            double averageSteps = reports.stream().mapToDouble(ReportDto.ReportDtoCommon::getSteps).average().orElse(0);
            double averageCaffeine = reports.stream().mapToDouble(ReportDto.ReportDtoCommon::getCaffeine).average().orElse(0);

            averageReport = ReportDto.ReportDtoAverage.builder()
                    .sleepScore(averageSleepScore)
                    .sleepDuration(averageSleepDuration)
                    .stressLevel(averageStressLevel)
                    .steps(averageSteps)
                    .caffeine(averageCaffeine)
                    .build();
        }

        // 최근 리포트
        ReportDto.ReportDtoCommon recentReport = reportRepository.findReportInfoRecent(member);

        return ReportDto.ReportDtoRes.builder()
                .todayReport(reportCommonToday)
                .averageReport(averageReport)
                .recentReport(recentReport)
                .build();
    }

    @Override
    public ReportDto.ReportDtoByDate getOneWeekReports(Member member) {
        List<Report> reports = reportRepository.findReportsForLastWeek(member);

        ReportDto.ReportDtoByDate oneWeekReportDto;

        List<SleepDto> sleeps = new ArrayList<>();
        double weekSleepDurations = 0.0;
        double weekSleepScores = 0.0;
        int cnt = 0;

        for (Report report : reports) {
            Health health = healthRepository.findByReport(report)
                    .orElseThrow(() -> new NotFoundException("Not Found Health"));
            weekSleepScores += health.getSleepScore();
            weekSleepDurations += health.getSleepDuration();
            cnt++;

            sleeps.add(SleepDto.builder()
                    .date(report.getSleepReportDate())
                    .sleepDuration(health.getSleepDuration())
                    .build());
        }

        double averageSleep = cnt != 0 ? weekSleepDurations / cnt : 0;
        double averageSleepScore = cnt != 0 ? weekSleepScores / cnt : 0;

        oneWeekReportDto = ReportDto.ReportDtoByDate.builder()
                .averageSleep(averageSleep)
                .averageSleepScore(averageSleepScore)
                .sleeps(sleeps)
                .totalDate(7)
                .build();

        return oneWeekReportDto;
    }

    @Override
    public ReportDto.ReportDtoByDate getReportsByDates(Member member, LocalDate startDate, LocalDate endDate) {
        List<Report> reports = reportRepository.findReportsByDate(member, startDate, endDate);

        ReportDto.ReportDtoByDate reportDtoByDate;

        List<SleepDto> sleeps = new ArrayList<>();
        double sleepDurations = 0.0;
        double sleepScores = 0.0;
        int cnt = 0;

        for (Report report : reports) {
            Health health = healthRepository.findByReport(report)
                    .orElseThrow(() -> new NotFoundException("Not Found Health"));
            sleepScores += health.getSleepScore();
            sleepDurations += health.getSleepDuration();
            cnt++;

            sleeps.add(SleepDto.builder()
                    .date(report.getSleepReportDate())
                    .sleepDuration(health.getSleepDuration())
                    .build());
        }

        double averageSleep = cnt != 0 ? sleepDurations / cnt : 0;
        double averageSleepScore = cnt != 0 ? sleepScores / cnt : 0;
        long total = ChronoUnit.DAYS.between(startDate, endDate);

        reportDtoByDate = ReportDto.ReportDtoByDate.builder()
                .averageSleep(averageSleep)
                .averageSleepScore(averageSleepScore)
                .sleeps(sleeps)
                .totalDate(total)
                .build();

        return reportDtoByDate;
    }

    @Override
    public ReportDto.ReportDtoCommon getReportByDate(Member member, LocalDate date) {
        return reportRepository.findReportByDate(member, date);
    }

    /*
        DEEP(5), LIGHT(4), SLEEPING(2), REM(6)에 가중치 주고, 나누기 전체 수면 시간 * 100
        (DEEP + REM + SLEEPING * 0.8 + LIGHT * 0.7) / 전체 수면 시간 * 100
     */
    private int calculateSleepScore(List<SleepStage> sleepStages, int sleepDuration) {
        int score;
        if (sleepDuration < 450) {
            score = (int) (4.4 * sleepDuration / 100);
        } else {
            score = 20;
        }

        for (var sleepStage : sleepStages) {
            int stage = sleepStage.getStage();
            int minutes = (int) Duration.between(sleepStage.getStartTime(), sleepStage.getEndTime()).toMinutes();


            if (stage == StageType.DEEP.value) {
                score += minutes;
            } else if (stage == StageType.REM.value) {
                score += minutes;
            } else if (stage == StageType.SLEEPING.value) {
                score += minutes * 0.8;
            } else if (stage == StageType.LIGHT.value) {
                score += minutes * 0.7;
            }
        }

        score = (score * 100) / sleepDuration;
        if (score < 40)
            score = 40;
        else if (score > 100) {
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
