package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.dto.DreamDto.DreamDtoReq;
import com.ssafy.cozytrain.api.service.DreamService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
    private final DreamService dreamService;

    @GetMapping("/{dreamId}")
    @Operation(summary = "꿈 조회")
    public ApiUtils.ApiResult<DreamDto.DreamDtoRes> getDream(@PathVariable Long dreamId) {
        return success(dreamService.getDream(dreamId));
    }

    @GetMapping
    @Operation(summary = "꿈 전체 조회")
    public ApiUtils.ApiResult<DreamDto.DreamDtoListRes> getDreams() {
        return success(dreamService.getDreams());
    }

    @PostMapping
    @Operation(summary = "꿈 등록")
    public ApiUtils.ApiResult<Boolean> createDream(@RequestBody @Valid DreamDtoReq dreamDtoReq) {
        return success(dreamService.saveDream(dreamDtoReq));
    }

    @PatchMapping("/{dreamId}")
    @Operation(summary = "꿈 수정")
    public ApiUtils.ApiResult<Boolean> updateDream(@PathVariable Long dreamId, @RequestBody @Valid DreamDtoReq dreamDtoReq) {
        return success(dreamService.updateDream(dreamId, dreamDtoReq));
    }
    
    @DeleteMapping("/{dreamId}")
    @Operation(summary = "꿈 삭제")
    public ApiUtils.ApiResult<Boolean> deleteDream(@PathVariable Long dreamId) {
        return success(dreamService.deleteDream(dreamId));
    }
}
