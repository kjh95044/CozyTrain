package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.CheckListDto;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/check-list")
@RequiredArgsConstructor
public class CheckListController {
    private final CheckListService checkListService;

    @PostMapping
    public ApiUtils.ApiResult<Integer> createBalanceGame(@RequestBody CheckListDto.CheckListSaveReq checkListSaveReq){
        return success(checkListService.checkListSave(checkListSaveReq));
    }
}
