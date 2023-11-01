package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;

public class CheckListDto {

    @Getter
    public static class CheckListSaveReq {
        private Integer checkListType;
        private String checkListName;

        @Builder
        public CheckListSaveReq(Integer checkListType, String checkListName) {
            this.checkListType = checkListType;
            this.checkListName = checkListName;
        }
    }
}
