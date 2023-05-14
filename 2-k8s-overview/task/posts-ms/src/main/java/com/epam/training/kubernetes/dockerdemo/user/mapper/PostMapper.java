package com.epam.training.kubernetes.dockerdemo.user.mapper;

import com.epam.training.kubernetes.dockerdemo.user.domain.model.Post;
import com.epam.training.kubernetes.dockerdemo.user.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source="createdAt", target = "postedAt")
    PostDto postToPostDto(Post post);

}
