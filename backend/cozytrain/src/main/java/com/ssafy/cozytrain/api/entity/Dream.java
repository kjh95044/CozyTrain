package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Dream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dreamId;
    private String dreamContent;
    private Integer dreamType;
    private LocalDate dreamDate;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Builder
    public Dream(String dreamContent, Integer dreamType, LocalDate dreamDate, Member member) {
        this.dreamContent = dreamContent;
        this.dreamType = dreamType;
        this.dreamDate = dreamDate;
        this.member = member;
    }

    @Override
    public String toString() {
        return "Dream{" +
                "id=" + dreamId +
                ", dreamContent='" + dreamContent + '\'' +
                ", dreamType=" + dreamType +
                ", dreamDate=" + dreamDate +
                ", member=" + member +
                '}';
    }
}
