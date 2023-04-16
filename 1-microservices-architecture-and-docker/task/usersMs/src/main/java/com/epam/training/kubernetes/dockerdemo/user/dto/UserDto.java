package com.epam.training.kubernetes.dockerdemo.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private int amountOfPosts;
}
