package com.epam.training.kubernetes.dockerdemo.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthorService {

    @Value("${users.ms.base.uri}")
    private String usersMsBaseUri;

    private final RestTemplate restTemplate;

    public void incrementAuthorPostsCount(Long userId) {
        restTemplate
                .getForEntity(usersMsBaseUri + "/users/" + userId + "/posts-count-increment", Void.class);
    }

    public void decrementAuthorPostsCount(Long userId) {
        restTemplate
                .getForEntity(usersMsBaseUri + "/users/" + userId + "posts-count-decrement", Void.class);
    }

}
