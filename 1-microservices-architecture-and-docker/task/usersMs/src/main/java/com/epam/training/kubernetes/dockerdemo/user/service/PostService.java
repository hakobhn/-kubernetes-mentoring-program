package com.epam.training.kubernetes.dockerdemo.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${post.ms.base.uri}")
    private String postMsBaseUri;

    private final RestTemplate restTemplate;

    public int getUserPostsCount(Long userId) {
        ResponseEntity<Integer> response = restTemplate
                .getForEntity(postMsBaseUri + "/userPosts/" + userId, Integer.class);
        return Optional.ofNullable(response.getBody()).orElse(0);
    }

}
