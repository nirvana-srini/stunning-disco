package com.stunningdisco.pomodoro.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TimerSettingsRequest(
    @Min(1) @Max(120) int focusMinutes,
    @Min(1) @Max(60) int shortBreakMinutes,
    @Min(1) @Max(60) int longBreakMinutes,
    @Min(1) @Max(12) int longBreakEvery,
    boolean autoStart
) {
}
