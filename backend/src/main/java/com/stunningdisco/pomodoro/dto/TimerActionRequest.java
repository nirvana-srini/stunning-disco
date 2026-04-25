package com.stunningdisco.pomodoro.dto;

import jakarta.validation.constraints.NotBlank;

public record TimerActionRequest(@NotBlank String action) {
}
