package com.epam.training.kubernetes.dockerdemo.user.service;

import com.epam.training.kubernetes.dockerdemo.user.exception.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.epam.training.kubernetes.dockerdemo.user.controller.ControllerEndpoints.USERS_URL;

@Service
@RequiredArgsConstructor
public class AuthorService {

    @Value("${users.ms.base.uri}")
    private String usersMsBaseUri;

    private final RestTemplate restTemplate;

    public void incrementAuthorPostsCount(Long userId) {
        String url = usersMsBaseUri + USERS_URL + "/" + userId + "/posts-count-increment";
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (RestClientException e) {
            throw new InternalException("Failed with " + url, e);
        }
    }

    public void decrementAuthorPostsCount(Long userId) {
        String url = usersMsBaseUri + USERS_URL + "/" + userId + "/posts-count-decrement";
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (RestClientException e) {
            throw new InternalException("Failed with " + url, e);
        }
    }

}
