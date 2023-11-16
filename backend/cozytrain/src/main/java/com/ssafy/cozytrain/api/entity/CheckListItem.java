package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CheckListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkListItemId;
    private String elsId;

    @ManyToOne
    @JoinColumn(name = "check_list_id", referencedColumnName = "check_list_id")
    private CheckList checkList;

    @Builder
    public CheckListItem(String elsId, CheckList checkList) {
        this.elsId = elsId;
        this.checkList = checkList;
    }
}
