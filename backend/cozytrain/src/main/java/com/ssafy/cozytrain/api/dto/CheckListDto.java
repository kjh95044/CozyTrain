package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;

public class CheckListDto {

    @Getter
    public static class CheckListSaveReq {
        private Integer checkListType;
        private String checkListBrand;
        private String checkListName;
    }
}
