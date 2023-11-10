package com.ssafy.cozytrain.api.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QList;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cozytrain.api.dto.HealthDto;
import com.ssafy.cozytrain.api.dto.ReportDto;
import com.ssafy.cozytrain.api.entity.*;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.ssafy.cozytrain.api.entity.QHealth.health;
import static com.ssafy.cozytrain.api.entity.QReport.report;
import static com.ssafy.cozytrain.api.entity.QSleepStage.sleepStage;
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
    public List<ReportDto.ReportDtoCommon> findReportsByMember(Member member) {

        return queryFactory
                .from(health)
                .join(report).on(health.report.eq(report))
                .join(report.member).on(report.member.eq(member))
                .leftJoin(sleepStage).on(sleepStage.health.eq(health))
                .where(report.member.eq(member))
                .transform(
                        groupBy(report.reportId).list(
                                Projections.constructor(ReportDto.ReportDtoCommon.class,
                                        report.sleepReportDate,
                                        Projections.constructor(Health.class,
                                                health.stressLevel, health.sleepDuration, health.steps, health.sleepScore
                                        ),
                                        report.caffeine,
                                        Projections.list(Projections.constructor(SleepStage.class,
                                                sleepStage.stage,
                                                sleepStage.startTime,
                                                sleepStage.endTime
                                        ))
                                )
                        )
                );
    }

    @Override
    public List<Report> findReportsForLastWeek(Member member) {
        QReport qReport = report;

        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minus(1, ChronoUnit.WEEKS);

        BooleanExpression predicate = qReport.member.eq(member)
                .and(qReport.sleepReportDate.between(lastWeek, today));

        return queryFactory.selectFrom(qReport)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Report> findReportsByDate(Member member, LocalDate startDate, LocalDate endDate) {
        QReport qReport = report;

        BooleanExpression predicate = qReport.member.eq(member)
                .and(qReport.sleepReportDate.between(startDate, endDate));

        return queryFactory.selectFrom(qReport)
                .where(predicate)
                .fetch();
    }

    @Override
    public ReportDto.ReportDtoCommon findReportInfoRecent(Member member) {
        List<ReportDto.ReportDtoCommon> reportDto = queryFactory
                .from(health)
                .join(report).on(health.report.eq(report))
                .join(report.member).on(report.member.eq(member))
                .leftJoin(sleepStage).on(sleepStage.health.eq(health))
                .where(report.sleepReportDate.ne(LocalDate.now())
                        .and(report.member.eq(member)))
                .orderBy(report.sleepReportDate.desc())
                .limit(1)
                .transform(
                        groupBy(report.reportId).list(
                                Projections.constructor(ReportDto.ReportDtoCommon.class,
                                        report.sleepReportDate,
                                        Projections.constructor(Health.class,
                                                health.stressLevel, health.sleepDuration, health.steps, health.sleepScore
                                        ),
                                        report.caffeine,
                                        Projections.list(Projections.constructor(SleepStage.class,
                                                sleepStage.stage,
                                                sleepStage.startTime,
                                                sleepStage.endTime
                                        ))
                                )
                        )
                );
        if (reportDto.isEmpty())
            return null;
        return reportDto.get(0);
    }

    @Override
    public ReportDto.ReportDtoCommon findReportByDate(Member member, LocalDate date) {
        List<ReportDto.ReportDtoCommon> reports = queryFactory
                .from(health)
                .join(report).on(health.report.eq(report))
                .join(report.member).on(report.member.eq(member))
                .leftJoin(sleepStage).on(sleepStage.health.eq(health))
                .where(report.sleepReportDate.eq(date)
                        .and(report.member.eq(member)))
                .transform(
                        groupBy(report.reportId).list(
                                Projections.constructor(ReportDto.ReportDtoCommon.class,
                                        report.sleepReportDate,
                                        Projections.constructor(Health.class,
                                                health.stressLevel, health.sleepDuration, health.steps, health.sleepScore
                                        ),
                                        report.caffeine,
                                        Projections.list(Projections.constructor(SleepStage.class,
                                                sleepStage.stage,
                                                sleepStage.startTime,
                                                sleepStage.endTime
                                        ))
                                )
                        )
                );
        if (reports.isEmpty())
            throw new NotFoundException(date + " 리포트가 없습니다.");
        return reports.get(0);
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
