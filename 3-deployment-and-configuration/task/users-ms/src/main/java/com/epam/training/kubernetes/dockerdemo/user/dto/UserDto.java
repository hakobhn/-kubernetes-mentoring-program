package com.epam.training.kubernetes.dockerdemo.user.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private long id;
    @NotBlank(message = "{user.username.required}")
    @Size(max = 50, min = 3, message = "{user.username.invalid}")
    private String username;
    @NotNull(message = "{user.posts.count.required}")
    @Min(value = 0, message = "{user.posts.count.required}")
    private Integer amountOfPosts = -1;
}
