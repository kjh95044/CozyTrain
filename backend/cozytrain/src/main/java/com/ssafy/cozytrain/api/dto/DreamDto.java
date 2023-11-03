package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.Dream;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DreamDto {

    @Getter
    @NoArgsConstructor
    public static class DreamDtoRes {
        private Long dreamId;
        private LocalDate dreamDate;
        private Integer dreamType;
        private String dreamContent;

        @Builder
        public DreamDtoRes(Long dreamId, LocalDate dreamDate, Integer dreamType, String dreamContent) {
            this.dreamId = dreamId;
            this.dreamDate = dreamDate;
            this.dreamType = dreamType;
            this.dreamContent = dreamContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DreamDtoListRes {
        private List<DreamDtoRes> dreamDtoResList;

        @Builder
        public DreamDtoListRes(List<Dream> dreamList) {
            this.dreamDtoResList = dreamList.stream().map((dream) -> {
                return DreamDtoRes.builder()
                        .dreamId(dream.getDreamId())
                        .dreamContent(dream.getDreamContent())
                        .dreamType(dream.getDreamType())
                        .dreamDate(dream.getDreamDate())
                        .build();
            }).collect(Collectors.toList());
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DreamDtoReq {
        @NonNull
        private Integer dreamType;
        @NonNull
        private String dreamContent;

        @Builder
        public DreamDtoReq(Integer dreamType, String dreamContent) {
            this.dreamType = dreamType;
            this.dreamContent = dreamContent;
        }
    }
}
