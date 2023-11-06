package com.ssafy.cozytrain.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
@NoArgsConstructor
@Getter
public class SleepDto {
    private double sleepDuration;
    private String dayOfWeekName;

    @Builder
    public SleepDto(LocalDate date, double sleepDuration) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        Locale locale = Locale.getDefault();

        this.dayOfWeekName = dayOfWeek.getDisplayName(TextStyle.FULL, locale);
        this.sleepDuration = sleepDuration;
    }

}
