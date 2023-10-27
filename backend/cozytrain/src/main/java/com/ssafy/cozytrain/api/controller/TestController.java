package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.entity.Test;
import com.ssafy.cozytrain.api.service.TestService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping()
    public ApiUtils.ApiResult<List<Test>> testTrain(){
        return success(testService.findAll());
    }

    @GetMapping("check")
    public ApiUtils.ApiResult<String> testCheck(){
        return success("check");
    }
}
