package com.epam.training.kubernetes.dockerdemo.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.epam.training.kubernetes.dockerdemo.user.controller.ControllerEndpoints.USERS_URL;

@Service
@RequiredArgsConstructor
public class AuthorService {

    @Value("${users.ms.base.uri}")
    private String usersMsBaseUri;

    private final RestTemplate restTemplate;

    public void incrementAuthorPostsCount(Long userId) {
        restTemplate
                .getForEntity(usersMsBaseUri + USERS_URL + "/" + userId + "/posts-count-increment", Void.class);
    }

    public void decrementAuthorPostsCount(Long userId) {
        restTemplate
                .getForEntity(usersMsBaseUri + USERS_URL + "/" + userId + "/posts-count-decrement", Void.class);
    }

}
