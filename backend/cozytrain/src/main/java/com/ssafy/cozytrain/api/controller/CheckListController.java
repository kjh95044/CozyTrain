package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.CheckListDto.*;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/check-list")
@RequiredArgsConstructor
public class CheckListController {
    private final CheckListService checkListService;

    @PostMapping
    @Operation(summary = "체크리스트 생성")
    public ApiUtils.ApiResult<Integer> createCheckList(@RequestBody @Valid CheckListDtoReq checkListDtoReq){
        return success(checkListService.checkListSave(checkListDtoReq));
    }

    @GetMapping
    @Operation(summary = "오늘 나의 체크리스트 목록 조회")
    public ApiUtils.ApiResult<CheckListTodayRes> getCheckList(){
        return success(checkListService.checkListToday());
    }

    @DeleteMapping("/{checkListId}")
    @Operation(summary = "나의 체크리스트 아이템 삭제")
    public ApiUtils.ApiResult<Integer> deleteCheckList(@PathVariable @ApiParam(value = "체크리스트 ID 값") Long checkListId){
        return success(checkListService.checkListDelete(checkListId));
    }

}
