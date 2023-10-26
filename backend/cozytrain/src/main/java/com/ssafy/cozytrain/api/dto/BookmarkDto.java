package com.ssafy.cozytrain.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class BookmarkDto {
    @NotNull(message = "elsId is not Null")
    private Long elsId;
}
