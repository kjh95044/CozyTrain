package com.ssafy.cozytrain.api.dto;

import com.ssafy.cozytrain.api.entity.Country;
import com.ssafy.cozytrain.api.entity.Item;
import lombok.Builder;
import lombok.Getter;

public class ItemDto {

    @Getter
    public static class ItemDtoRes{
        private String itemName;
        private String itemImgUrl;
        private String itemDescription;
        private String country;

        @Builder
        public ItemDtoRes(Item item){
            this.itemName = item.getItemName();
            this.itemImgUrl = item.getItemImgUrl();
            this. itemDescription = item.getItemDescription();
            this.country = item.getCountry().getCountryName();
        }
    }
}
