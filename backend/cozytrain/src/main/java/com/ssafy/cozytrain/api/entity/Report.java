package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elasticsearch.monitor.os.OsStats;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Report {
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private LocalDate sleepReportDate;
    private LocalDateTime updatedAt;
    private int caffeine;
    private int moveDist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private CheckList checkList;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private Health health;

    @Builder
    public Report(Member member, int caffeine, LocalDate sleepReportDate, LocalDateTime updatedAt) {
        this.member = member;
        this.caffeine = caffeine;
        this.sleepReportDate = sleepReportDate;
        this.updatedAt = updatedAt;
    }

    public void updateUpdatedAt(LocalDateTime dateTime){
        this.updatedAt = dateTime;
    }
    public void updateMoveDist(int moveDist) { this.moveDist = moveDist; }
}
