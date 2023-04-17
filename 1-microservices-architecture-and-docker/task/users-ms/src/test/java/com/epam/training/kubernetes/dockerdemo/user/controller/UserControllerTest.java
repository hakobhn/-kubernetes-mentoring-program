package com.epam.training.kubernetes.dockerdemo.user.controller;

import com.epam.training.kubernetes.dockerdemo.user.domain.repository.UserRepository;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserDto;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:data/schema-users.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:data/data-users.sql", executionPhase = BEFORE_TEST_METHOD)
})
class UserControllerTest {

    private final static String USERS_URL = "/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldAddNewUser() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders
                .get(USERS_URL);

        UserInput userInput = new UserInput();
        userInput.setUsername("testUser");

        // when
        MvcResult result = mockMvc.perform(post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInput)))
                .andDo(print())
                .andReturn();

        UserDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(), UserDto.class);

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(6);
        assertThat(actual.getUsername()).isEqualTo("testUser");
        assertThat(actual.getAmountOfPosts()).isZero();
    }

    @Test
    void shouldFailToAddInvalidUser() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders
                .get(USERS_URL);

        UserInput userInput = new UserInput();
        userInput.setUsername("te");

        // when
        mockMvc.perform(post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userInput)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRetrieveUserById() throws Exception {
        // given
        RequestBuilder request = MockMvcRequestBuilders
                .get(USERS_URL + "/1");

        // when
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        UserDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(), UserDto.class);

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getUsername()).isEqualTo("tonny-cross");
        assertThat(actual.getAmountOfPosts()).isEqualTo(110);
    }
}