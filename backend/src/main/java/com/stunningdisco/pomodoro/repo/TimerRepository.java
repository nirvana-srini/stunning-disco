package com.stunningdisco.pomodoro.repo;

import com.stunningdisco.pomodoro.model.TimerState;

public interface TimerRepository {
    TimerState get();
    void save(TimerState state);
}
