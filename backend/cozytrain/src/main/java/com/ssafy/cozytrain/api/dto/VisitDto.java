package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VisitDto {
    @Getter
    @NoArgsConstructor
    public static class ContinentDto{
        private String continent;
        private Boolean visit;

        @Builder
        public ContinentDto(String continent, Boolean visit){
            this.continent = continent;
            this.visit = visit;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class CountryDto{
        private String country;
        private Boolean visit;

        @Builder
        public CountryDto(String country, Boolean visit){
            this.country = country;
            this.visit = visit;
        }
    }
}
