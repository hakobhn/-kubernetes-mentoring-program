package com.epam.training.kubernetes.dockerdemo.user.controller;


import com.epam.training.kubernetes.dockerdemo.user.domain.model.Post;
import com.epam.training.kubernetes.dockerdemo.user.domain.repository.PostRepository;
import com.epam.training.kubernetes.dockerdemo.user.dto.PostInput;
import com.epam.training.kubernetes.dockerdemo.user.service.AuthorService;
import com.epam.training.kubernetes.dockerdemo.user.service.PostService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static com.epam.training.kubernetes.dockerdemo.user.controller.ControllerEndpoints.POSTS_URL;
import static com.epam.training.kubernetes.dockerdemo.user.controller.ControllerEndpoints.USERS_URL;
import static org.mockito.Mockito.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.Header.header;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
public class PostControllerTest {

    private static final ClientAndServer mockServerClient = startClientAndServer();

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private AuthorService authorService;

    private static final long authorId = 1;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("users.ms.base.uri", () -> "http://127.0.0.1:" + mockServerClient.getPort());
    }

    @BeforeEach
    public void setUpEach() {
        mockServerClient.reset();
    }

    @AfterAll
    public static void stopMockServer() {
        mockServerClient.stop();
    }

    private void createExpectationForAuthorPostsCountIncrement() {
        mockServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(USERS_URL + "/" + authorId + "/posts-count-increment")
                )
                .respond(
                        response()
                                .withStatusCode(HttpStatus.OK.value())
                                .withHeaders(header("Content-Type", "application/json; charset=utf-8"))
                                .withBody("{\n" +
                                        "    \"id\": "+authorId+",\n" +
                                        "    \"username\": \"superTest\",\n" +
                                        "    \"amountOfPosts\": 4\n" +
                                        "}")
                );
    }

    private void createExpectationForAuthorPostsCountDecrement() {
        mockServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath(USERS_URL + "/" + authorId + "/posts-count-decrement")
                )
                .respond(
                        response()
                                .withStatusCode(HttpStatus.OK.value())
                                .withHeaders(header("Content-Type", "application/json; charset=utf-8"))
                                .withBody("{\n" +
                                        "    \"id\": "+authorId+",\n" +
                                        "    \"username\": \"superTest\",\n" +
                                        "    \"amountOfPosts\": 3\n" +
                                        "}")
                );
    }


    @Test
    void shouldSuccessfullyAddNewPostMessage() {
        // given
        PostInput postInput = new PostInput();
        postInput.setAuthorId(authorId);
        postInput.setText("Text message");

        Post post = new Post();
        post.setAuthorId(authorId);
        post.setText("Text message");

        createExpectationForAuthorPostsCountIncrement();

        when(postRepository.save(post)).thenReturn(post);

        // when
        webTestClient
                .post()
                .uri(POSTS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(postInput)
                .exchange()
                .expectStatus().is2xxSuccessful();

        verify(postRepository, only()).save(any());
    }

    @Test
    void shouldSuccessfullyDeletePostMessage() {
        // given
        String postId = "1";
        createExpectationForAuthorPostsCountDecrement();

        Post post = new Post();
        post.setId(postId);
        post.setAuthorId(authorId);
        post.setText("Text message");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        doNothing().when(postRepository).deleteById(postId);

        // when
        webTestClient
                .delete()
                .uri(POSTS_URL + "/" + postId)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

}
