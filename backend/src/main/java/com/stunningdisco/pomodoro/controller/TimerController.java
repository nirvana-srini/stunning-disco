package com.stunningdisco.pomodoro.controller;

import com.stunningdisco.pomodoro.dto.TimerActionRequest;
import com.stunningdisco.pomodoro.dto.TimerSettingsRequest;
import com.stunningdisco.pomodoro.model.TimerState;
import com.stunningdisco.pomodoro.service.TimerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/public/timer")
    public TimerState getTimer() {
        return timerService.currentState();
    }

    @PostMapping("/public/timer/action")
    public TimerState timerAction(@Valid @RequestBody TimerActionRequest request) {
        return timerService.applyAction(request.action());
    }

    @PostMapping("/public/timer/settings")
    public TimerState updateSettings(@Valid @RequestBody TimerSettingsRequest request) {
        return timerService.updateSettings(request);
    }
}
