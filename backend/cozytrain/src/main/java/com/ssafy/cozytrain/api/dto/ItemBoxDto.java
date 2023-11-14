package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;

public class ItemBoxDto {
    @Getter
    public static class ItemBoxDtoRes{
        private long countryId;
        private String country;
        private int cnt;

        @Builder
        ItemBoxDtoRes(long countryId, String country, int cnt){
            this.countryId = countryId;
            this.country = country;
            this.cnt = cnt;
        }
    }
}
