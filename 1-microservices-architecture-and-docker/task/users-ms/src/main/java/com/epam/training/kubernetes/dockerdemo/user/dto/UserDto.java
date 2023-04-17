package com.epam.training.kubernetes.dockerdemo.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private long id;
    @NotBlank(message = "${user.username.required}")
    @Size(max = 10, min = 3, message = "${user.username.invalid}")
    private String username;
    @NotNull(message = "${user.posts.count.required}")
    private int amountOfPosts;
}
