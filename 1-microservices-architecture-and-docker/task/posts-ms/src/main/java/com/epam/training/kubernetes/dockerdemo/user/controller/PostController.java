package com.epam.training.kubernetes.dockerdemo.user.controller;

import com.epam.training.kubernetes.dockerdemo.user.dto.PostDto;
import com.epam.training.kubernetes.dockerdemo.user.dto.PostInput;
import com.epam.training.kubernetes.dockerdemo.user.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    ResponseEntity<PostDto> create(@Valid @RequestBody PostInput postInput) {
        return ResponseEntity.ok(postService.create(postInput));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PostDto> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<PostDto> update(@PathVariable("id") String id,
                                   @Valid @RequestBody PostInput postInput) {
        return ResponseEntity.ok(postService.update(id, postInput));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }

}
