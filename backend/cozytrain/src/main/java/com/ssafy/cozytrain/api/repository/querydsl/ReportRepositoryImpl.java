package com.ssafy.cozytrain.api.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cozytrain.api.dto.HealthDto;
import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.dto.SleepStageDto;
import com.ssafy.cozytrain.api.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ReportRepositoryImpl implements ReportRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<ReportDto.ReportDtoRes> findReportInfo(Long reportId) {
        QReport qReport = QReport.report;
        QHealth qHealth = QHealth.health;
        QSleepStage qSleepStage = QSleepStage.sleepStage;

        // report 말고 health 부터 해보자
        List<HealthDto.HealthDtoRes> health = queryFactory
                .from(qHealth)
                .leftJoin(qSleepStage).on(qHealth.healthId.eq(qSleepStage.health.healthId))
                .where(qHealth.report.reportId.eq(reportId))
                .transform(groupBy(qHealth.healthId).list(Projections.constructor(HealthDto.HealthDtoRes.class,
                        qHealth.stressLevel, qHealth.sleepDuration, qHealth.sleepScore, qHealth.steps,
                                list(Projections.constructor(SleepStageDto.SleepStageDtoRes.class,
                                        qSleepStage.stage, qSleepStage.startTime, qSleepStage.endTime))
                        )
                ));

//                QBalanceGame bg = QBalanceGame.balanceGame;
//                QBalanceGameList bgl = QBalanceGameList.balanceGameList;
//                List<BalanceGameDto> balanceGame = queryFactory
//                    .from(bg)
//                    .leftJoin(bgl).on(bg.balanceGameId.eq(bgl.balanceGame.balanceGameId))
//                    .where(bg.balanceGameId.eq(balanceGameId))
//                    .transform(groupBy(bg.balanceGameId).list(Projections.constructor(BalanceGameDto.class,
//                            bg.balanceGameId, bg.balanceGameTitle, bg.balanceGameTime, bg.goodCount, bg.normalCount, bg.badCount,
//                            list(Projections.constructor(BalanceGameListDto.class,
//                                    bgl.balanceGameListId, bgl.balanceGameOne, bgl.balanceGameTwo, bgl.balanceOrder))
//                    )));

//        List<ReportDto.ReportDtoRes> report = queryFactory
//                .from(qReport)
//                .innerJoin(qHealth).on(qReport.reportId.eq(qHealth.report.reportId))
//                .innerJoin(qSleepStage).on(qHealth.healthId.eq(qSleepStage.health.healthId))
//                .where(qReport.reportId.eq(reportId))
//                .transform(groupBy(qReport.reportId).list(Projections.constructor(ReportDto.ReportDtoRes.class,
//                        list(Projections.constructor(HealthDto.HealthDtoRes.class,
//                                qHealth.sleepDuration, qHealth.sleepScore, qHealth.steps, qHealth.stressLevel,
//                                list(Projections.constructor(SleepStageDto.SleepStageDtoRes.class,
//                                        qSleepStage.stage, qSleepStage.startTime, qSleepStage.endTime))
//                        ))
//                )));
        log.debug("음?" + health.toString());

        return Optional.empty();
    }

    @Override
    public List<Report> findReportsForLastWeek(Member member) {
        QReport qReport = QReport.report;

        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minus(1, ChronoUnit.WEEKS);

        BooleanExpression predicate = qReport.member.eq(member)
                .and(qReport.sleepReportDate.between(lastWeek, today));

        return queryFactory.selectFrom(qReport)
                .where(predicate)
                .fetch();
    }

//    @Override
//    public Optional<BalanceGameDto> findBalanceGame(Long balanceGameId) {
//        QBalanceGame bg = QBalanceGame.balanceGame;
//        QBalanceGameList bgl = QBalanceGameList.balanceGameList;
//        List<BalanceGameDto> balanceGame =queryFactory
//                .from(bg)
//                .leftJoin(bgl).on(bg.balanceGameId.eq(bgl.balanceGame.balanceGameId))
//                .where(bg.balanceGameId.eq(balanceGameId))
//                .transform(groupBy(bg.balanceGameId).list(Projections.constructor(BalanceGameDto.class,
//                        bg.balanceGameId, bg.balanceGameTitle, bg.balanceGameTime, bg.goodCount, bg.normalCount, bg.badCount,
//                        list(Projections.constructor(BalanceGameListDto.class,
//                                bgl.balanceGameListId, bgl.balanceGameOne, bgl.balanceGameTwo, bgl.balanceOrder))
//                )));
//
//        return Optional.ofNullable(balanceGame.get(0));
//    }
}
