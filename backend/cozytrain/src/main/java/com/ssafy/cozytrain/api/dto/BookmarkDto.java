package com.ssafy.cozytrain.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDto {
    @NotNull(message = "elsId is not Null")
    private String elsId;
}
