package com.stunningdisco.pomodoro.repo;

import com.stunningdisco.pomodoro.model.SessionType;
import com.stunningdisco.pomodoro.model.TimerState;
import com.stunningdisco.pomodoro.model.TimerStatus;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicReference;

@Repository
public class InMemoryTimerRepository implements TimerRepository {

    private final AtomicReference<TimerState> state = new AtomicReference<>(
        new TimerState(SessionType.FOCUS, TimerStatus.IDLE, 25 * 60, 0, true)
    );

    @Override
    public TimerState get() {
        return state.get();
    }

    @Override
    public void save(TimerState nextState) {
        state.set(nextState);
    }
}
