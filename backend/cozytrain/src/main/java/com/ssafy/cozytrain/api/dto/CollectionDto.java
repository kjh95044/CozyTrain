package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class CollectionDto {

    @Getter
    @NoArgsConstructor
    public static class CollectionDtoRes{
        private List<CollectionItemDto> items;

        @Builder
        public CollectionDtoRes(List<CollectionItemDto> items){
            this.items = items;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class CollectionItemDto{
        private String itemName;
        private String itemImgUrl;
        private String itemDescription;
        private String country;
        private boolean isOwn;

        @Builder
        public CollectionItemDto(String itemName, String itemImgUrl, String itemDescription, String country, boolean isOwn){
            this.itemName = itemName;
            this.itemImgUrl = itemImgUrl;
            this.itemDescription = itemDescription;
            this.country = country;
            this.isOwn = isOwn;
        }
    }


}
