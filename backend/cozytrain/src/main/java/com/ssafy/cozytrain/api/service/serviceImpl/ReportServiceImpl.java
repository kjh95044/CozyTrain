package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.HealthDto;
import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.dto.SleepStageDto;
import com.ssafy.cozytrain.api.entity.Health;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Report;
import com.ssafy.cozytrain.api.entity.SleepStage;
import com.ssafy.cozytrain.api.repository.HealthRepository;
import com.ssafy.cozytrain.api.repository.ReportRepository;
import com.ssafy.cozytrain.api.repository.SleepStageRepository;
import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

        Report report = Report.builder()
                .member(member)
                .sleepReportDate(LocalDateTime.now())
                .build();

        Long reportId = reportRepository.save(report).getReportId();

        HealthDto.HealthDtoReq healthDtoReq = reportDtoReq.getHealth();
        List<SleepStageDto.SleepStageDtoReq> sleepStagesReq = healthDtoReq.getSleepStages();

        Health health = Health.builder()
                .sleepDuration(healthDtoReq.getSleepDuration())
                .stressLevel(healthDtoReq.getStressLevel())
                .steps(healthDtoReq.getSteps())
                .report(report)
                .build();
        healthRepository.save(health);


        for(SleepStageDto.SleepStageDtoReq sleepStageDtoReq : sleepStagesReq){
            SleepStage sleepStage = SleepStage.builder()
                    .stage(sleepStageDtoReq.getStage())
                    .startTime(sleepStageDtoReq.getStartTime())
                    .endTime(sleepStageDtoReq.getEndTime())
                    .health(health)
                    .build();
            sleepStageRepository.save(sleepStage);
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

        log.info("요기 : " + sleepStages.toString());

        // TODO: 수면 점수 계산해서 reportDtoRes 반환 해야함.
        return null;
    }

    private int calculateSleepScore(){
        int score = 0;
        return score;
    }
}
