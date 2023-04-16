package com.epam.training.kubernetes.dockerdemo.controller;

import com.epam.training.kubernetes.dockerdemo.user.dto.UserDto;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserInput;
import com.epam.training.kubernetes.dockerdemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserDto> create(@Valid @RequestBody UserInput userInput) {
        return ResponseEntity.ok(userService.create(userInput));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<UserDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<UserDto> update(@PathVariable("id") Long id,
                                   @Valid @RequestBody UserInput userInput) {
        return ResponseEntity.ok(userService.update(id, userInput));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
