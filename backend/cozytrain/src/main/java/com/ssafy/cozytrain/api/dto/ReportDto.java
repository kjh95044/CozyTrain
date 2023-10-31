package com.ssafy.cozytrain.api.dto;

import lombok.Getter;


public class ReportDto {
    @Getter
    public static class ReportDtoReq{
        private HealthDto.HealthDtoReq health;
    }
}
