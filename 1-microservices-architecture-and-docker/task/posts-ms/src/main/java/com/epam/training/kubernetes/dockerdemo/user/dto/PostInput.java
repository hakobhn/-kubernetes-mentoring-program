package com.epam.training.kubernetes.dockerdemo.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostInput {
    @NotNull(message = "${post.author.required}")
    private Long authorId;
    @NotBlank(message = "${post.text.required}")
    private String text;
}
