package com.epam.training.kubernetes.dockerdemo.user.domain;

import com.epam.training.kubernetes.dockerdemo.user.domain.model.User;
import com.epam.training.kubernetes.dockerdemo.user.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanUp() {
        this.userRepository.deleteAll();
    }

    @Test
    void shouldReturnListOfPostWithMatchingAuthor() {
        User firstUser = new User();
        firstUser.setUsername("firstUser");
        firstUser.setNumberOfPosts(3);
        this.userRepository.save(firstUser);

        User secondUser = new User();
        secondUser.setUsername("secondUser");
        secondUser.setNumberOfPosts(0);
        this.userRepository.save(secondUser);

        Iterable<User> users = userRepository.findAll();

        assertThat(users).hasSize(2);
    }

}