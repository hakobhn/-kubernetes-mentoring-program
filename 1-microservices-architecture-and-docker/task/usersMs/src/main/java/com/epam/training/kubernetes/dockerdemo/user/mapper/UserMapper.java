package com.epam.training.kubernetes.dockerdemo.user.mapper;

import com.epam.training.kubernetes.dockerdemo.user.domain.model.User;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserDto;
import com.epam.training.kubernetes.dockerdemo.user.service.PostService;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserMapper {

    @Autowired
    private PostService postService;

    @Mapping(target = "amountOfPosts", expression="java(postService.getUserPostsCount(id))")
    public abstract UserDto userToUserDto(User user);

}
