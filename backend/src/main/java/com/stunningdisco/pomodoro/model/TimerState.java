package com.stunningdisco.pomodoro.model;

public record TimerState(
    SessionType sessionType,
    TimerStatus status,
    int remainingSeconds,
    int completedFocusSessions,
    boolean autoStart
) {
}
