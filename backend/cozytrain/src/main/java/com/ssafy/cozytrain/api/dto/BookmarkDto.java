package com.ssafy.cozytrain.api.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BookmarkDto {
    @NotNull(message = "elsId is not Null")
    private Long elsId;

}
