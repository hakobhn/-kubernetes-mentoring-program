package com.epam.training.kubernetes.dockerdemo.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostInput {
    @NotNull(message = "${post.author.required}")
    private Long authorId;
    @NotBlank(message = "${post.text.required}")
    private String text;
}
