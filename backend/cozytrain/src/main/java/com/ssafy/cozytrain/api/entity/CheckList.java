package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@Entity
@Getter
@NoArgsConstructor
public class CheckList {
    @Id
    @Column(name = "check_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkListId;
    private LocalDate checkListDate;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "checkList", cascade = CascadeType.ALL)
    private List<CheckListItem> checkList;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id")
    private Report report;

    @Builder
    public CheckList(LocalDate checkListDate, LocalDateTime updatedAt, Member member) {
        this.checkListDate = checkListDate;
        this.updatedAt = updatedAt;
        this.member = member;
    }
}
