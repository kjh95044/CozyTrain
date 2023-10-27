package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.dto.DreamDto.DreamDtoReq;
import com.ssafy.cozytrain.api.service.DreamService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
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
    public ApiUtils.ApiResult<DreamDto.DreamDtoRes> getDream(@PathVariable Long dreamId) {
        return success(dreamService.getDream(dreamId));
    }

    @GetMapping
    public ApiUtils.ApiResult<DreamDto.DreamDtoListRes> getDreams() {
        return success(dreamService.getDreams());
    }

    @PostMapping
    public ApiUtils.ApiResult<Boolean> createDream(@RequestBody @Valid DreamDtoReq dreamDtoReq) {
        return success(dreamService.saveDream(dreamDtoReq));
    }
}
