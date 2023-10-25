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
    private Integer checkListItemType;
    private String checkListItemBrand;
    private String checkListItemName;

    @ManyToOne
    @JoinColumn(name = "check_list_id", referencedColumnName = "check_list_id")
    private CheckList checkList;

    @Builder
    public CheckListItem(Integer checkListItemType, String checkListItemBrand, String checkListItemName, CheckList checkList) {
        this.checkListItemType = checkListItemType;
        this.checkListItemBrand = checkListItemBrand;
        this.checkListItemName = checkListItemName;
        this.checkList = checkList;
    }
}
