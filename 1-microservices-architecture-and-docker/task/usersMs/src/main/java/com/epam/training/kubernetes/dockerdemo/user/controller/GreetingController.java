package com.epam.training.kubernetes.dockerdemo.user.controller;

import com.epam.training.kubernetes.dockerdemo.user.config.LocalizedMessageProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/greeting")
@RequiredArgsConstructor
public class GreetingController {

    private final LocalizedMessageProvider messageProvider;

    @GetMapping
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(messageProvider.getMessage("greeting.message"));
    }
}
