package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private ReportService reportService;

}
