package com.epam.training.kubernetes.dockerdemo.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserInput {
    @NotBlank(message = "${user.username.required}")
    @Size(max = 50, min = 3, message = "${user.username.invalid}")
    private String username;
}
