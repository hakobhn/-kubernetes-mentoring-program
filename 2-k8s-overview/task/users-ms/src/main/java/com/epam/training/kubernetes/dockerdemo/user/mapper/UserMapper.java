package com.epam.training.kubernetes.dockerdemo.user.mapper;

import com.epam.training.kubernetes.dockerdemo.user.domain.model.User;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="numberOfPosts", target = "amountOfPosts")
    UserDto userToUserDto(User user);

}
