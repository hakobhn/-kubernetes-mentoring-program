package com.epam.training.kubernetes.dockerdemo.user.controller;

import com.epam.training.kubernetes.dockerdemo.user.dto.UserDto;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserInput;
import com.epam.training.kubernetes.dockerdemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.epam.training.kubernetes.dockerdemo.user.controller.ControllerEndpoints.USERS_URL;

@Slf4j
@RestController
@RequestMapping(value = USERS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
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
                                   @Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @GetMapping(value = "/{id}/posts-count-increment")
    ResponseEntity<UserDto> incrementPostCount(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updatePostCount(id, 1));
    }

    @GetMapping(value = "/{id}/posts-count-decrement")
    ResponseEntity<UserDto> decrementPostCount(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updatePostCount(id, -1));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
