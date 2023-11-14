package com.ssafy.cozytrain.api.controller.elastic;

import com.ssafy.cozytrain.api.entity.elastic.CaffeineCompleteDocument;
import com.ssafy.cozytrain.api.entity.elastic.CaffeineDocument;
import com.ssafy.cozytrain.api.service.elastic.CaffeineService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/caffeine")
@RequiredArgsConstructor
public class CaffeineController {

    private final CaffeineService caffeineService;

    @Operation(summary = "카페인 검색")
    @GetMapping("/search")
    public ApiUtils.ApiResult<Page<CaffeineDocument>> searchCaffeine(@RequestParam(name = "searchName") String searchName, Pageable pageable){
        log.info(searchName);
        Page<CaffeineDocument> mediaList = caffeineService.searchCaffeine(searchName, pageable);
        return success(mediaList);
    }

    @Operation(summary = "카페인 자동완성 검색")
    @GetMapping("/complete")
    public ApiUtils.ApiResult<Page<CaffeineCompleteDocument>> searchCaffeineComplete(@RequestParam(name = "searchName") String searchName, Pageable pageable){
        log.info(searchName);
        Page<CaffeineCompleteDocument> mediaList = caffeineService.searchCaffeineComplete(searchName, pageable);
        return success(mediaList);
    }

    @Operation(summary = "카페인 음료 1개 조회")
    @GetMapping("/search/drink")
    public ApiUtils.ApiResult<CaffeineDocument> caffeineInfo(@RequestParam(name = "id") String id){
        log.info(id);
        return success(caffeineService.getCaffeineInfo(id));
    }

}
