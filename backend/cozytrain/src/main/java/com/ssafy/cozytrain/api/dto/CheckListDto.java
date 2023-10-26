package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.CheckListItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class CheckListDto {

    @Getter
    @NoArgsConstructor
    public static class CheckListDtoReq {
        private Long checkListId;

        @Builder
        public CheckListDtoReq(Long checkListId) {
            this.checkListId = checkListId;
        }
    }

    @Getter
    public static class CheckListTodayRes {
        private List<CheckListDtoReq> checkListDtoList;

        @Builder
        public CheckListTodayRes(List<CheckListItem> checkListItem) {
            this.checkListDtoList = checkListItem.stream()
                    .map((item) -> {
                        return CheckListDtoReq.builder().checkListId(item.getElsId()).build();
                    })
                    .collect(Collectors.toList());
        }
    }
}
