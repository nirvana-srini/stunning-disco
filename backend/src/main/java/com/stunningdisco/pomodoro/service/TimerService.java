package com.stunningdisco.pomodoro.service;

import com.stunningdisco.pomodoro.dto.TimerSettingsRequest;
import com.stunningdisco.pomodoro.model.SessionType;
import com.stunningdisco.pomodoro.model.TimerState;
import com.stunningdisco.pomodoro.model.TimerStatus;
import com.stunningdisco.pomodoro.repo.TimerRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimerService {

    private final TimerRepository timerRepository;
    private final SimpMessagingTemplate messagingTemplate;

    private volatile int focusSeconds = 25 * 60;
    private volatile int shortBreakSeconds = 5 * 60;
    private volatile int longBreakSeconds = 15 * 60;
    private volatile int longBreakEvery = 4;

    public TimerService(TimerRepository timerRepository, SimpMessagingTemplate messagingTemplate) {
        this.timerRepository = timerRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public TimerState currentState() {
        return timerRepository.get();
    }

    public synchronized TimerState applyAction(String action) {
        TimerState state = timerRepository.get();
        TimerState nextState = switch (action.toLowerCase()) {
            case "start" -> new TimerState(state.sessionType(), TimerStatus.RUNNING, state.remainingSeconds(), state.completedFocusSessions(), state.autoStart());
            case "pause" -> new TimerState(state.sessionType(), TimerStatus.PAUSED, state.remainingSeconds(), state.completedFocusSessions(), state.autoStart());
            case "resume" -> new TimerState(state.sessionType(), TimerStatus.RUNNING, state.remainingSeconds(), state.completedFocusSessions(), state.autoStart());
            case "reset" -> resetCurrentSession(state);
            case "skip" -> transitionToNextSession(state);
            default -> state;
        };

        timerRepository.save(nextState);
        publish(nextState);
        return nextState;
    }

    public synchronized TimerState updateSettings(TimerSettingsRequest request) {
        this.focusSeconds = request.focusMinutes() * 60;
        this.shortBreakSeconds = request.shortBreakMinutes() * 60;
        this.longBreakSeconds = request.longBreakMinutes() * 60;
        this.longBreakEvery = request.longBreakEvery();

        TimerState state = timerRepository.get();
        TimerState updatedState = new TimerState(
            state.sessionType(),
            state.status(),
            defaultDuration(state.sessionType()),
            state.completedFocusSessions(),
            request.autoStart()
        );

        timerRepository.save(updatedState);
        publish(updatedState);
        return updatedState;
    }

    @Scheduled(fixedRate = 1000)
    public synchronized void tick() {
        TimerState state = timerRepository.get();
        if (state.status() != TimerStatus.RUNNING) {
            return;
        }

        int remaining = Math.max(0, state.remainingSeconds() - 1);
        TimerState updated = new TimerState(
            state.sessionType(),
            state.status(),
            remaining,
            state.completedFocusSessions(),
            state.autoStart()
        );

        if (remaining == 0) {
            updated = transitionToNextSession(updated);
            if (!updated.autoStart()) {
                updated = new TimerState(
                    updated.sessionType(),
                    TimerStatus.PAUSED,
                    updated.remainingSeconds(),
                    updated.completedFocusSessions(),
                    false
                );
            }
        }

        timerRepository.save(updated);
        publish(updated);
    }

    private TimerState resetCurrentSession(TimerState state) {
        return new TimerState(state.sessionType(), TimerStatus.IDLE, defaultDuration(state.sessionType()), state.completedFocusSessions(), state.autoStart());
    }

    private TimerState transitionToNextSession(TimerState state) {
        if (state.sessionType() == SessionType.FOCUS) {
            int completed = state.completedFocusSessions() + 1;
            SessionType nextType = (completed % longBreakEvery == 0) ? SessionType.LONG_BREAK : SessionType.SHORT_BREAK;
            return new TimerState(nextType, TimerStatus.RUNNING, defaultDuration(nextType), completed, state.autoStart());
        }

        return new TimerState(SessionType.FOCUS, TimerStatus.RUNNING, defaultDuration(SessionType.FOCUS), state.completedFocusSessions(), state.autoStart());
    }

    private int defaultDuration(SessionType sessionType) {
        return switch (sessionType) {
            case FOCUS -> focusSeconds;
            case SHORT_BREAK -> shortBreakSeconds;
            case LONG_BREAK -> longBreakSeconds;
        };
    }

    private void publish(TimerState state) {
        messagingTemplate.convertAndSend("/topic/timer", state);
    }
}
