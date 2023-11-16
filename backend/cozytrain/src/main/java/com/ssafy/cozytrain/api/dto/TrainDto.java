package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class TrainDto {

    @Getter
    public static class TrainDtoRes{

    }

    @Getter
    public static class TrainCurInfoDto{
        private final String region;
        private final String country;
        private final String regionKor;
        private final String countryKor;
        private final int regionNum;
        private final int dist;
        private final int area;
        private final int totalDist;

        @Builder
        public TrainCurInfoDto(String region, String country, String regionKor, String countryKor, int regionNum, int dist, int area, int totalDist){
            this.region = region;
            this.country = country;
            this.regionKor = regionKor;
            this.countryKor = countryKor;
            this.regionNum = regionNum;
            this.dist = dist;
            this.area = area;
            this.totalDist = totalDist;
        }
    }
}
