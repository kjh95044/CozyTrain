package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.CheckListDto.*;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/check-list")
@RequiredArgsConstructor
public class CheckListController {
    private final CheckListService checkListService;

    @PostMapping
    public ApiUtils.ApiResult<Integer> createCheckList(@RequestBody CheckListDtoReq checkListDtoReq){
        return success(checkListService.checkListSave(checkListDtoReq));
    }

}
