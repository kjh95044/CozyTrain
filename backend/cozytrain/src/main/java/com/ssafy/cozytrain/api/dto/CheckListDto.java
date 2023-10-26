package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.CheckListItem;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class CheckListDto {

    @Getter
    @NoArgsConstructor
    public static class CheckListDtoReq {

        @ApiParam(value = "ELS ID 값")
        @NotNull(message = "elsId is not Null")
        private Long elsId; // dataId

        @Builder
        public CheckListDtoReq(Long elsId) {
            this.elsId = elsId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class CheckListRes {
        private Long checkListId;
        private Long elsId;

        @Builder
        public CheckListRes(Long checkListId, Long elsId) {
            this.checkListId = checkListId;
            this.elsId = elsId;
        }
    }

    @Getter
    public static class CheckListTodayRes {
        private List<CheckListRes> checkListDtoList;

        @Builder
        public CheckListTodayRes(List<CheckListItem> checkListItem) {
            this.checkListDtoList = checkListItem.stream()
                    .map((item) -> {
                        return CheckListRes.builder()
                                .checkListId(item.getCheckListItemId())
                                .elsId(item.getElsId())
                                .build();
                    })
                    .collect(Collectors.toList());
        }
    }
}
