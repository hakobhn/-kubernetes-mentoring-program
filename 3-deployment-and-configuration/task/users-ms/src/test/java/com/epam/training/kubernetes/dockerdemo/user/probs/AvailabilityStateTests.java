package com.epam.training.kubernetes.dockerdemo.user.probs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvailabilityStateTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ApplicationAvailability applicationAvailability;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldBeAvailable() {
        assertThat(applicationAvailability.getLivenessState())
                .isEqualTo(LivenessState.CORRECT);
        assertThat(applicationAvailability.getReadinessState())
                .isEqualTo(ReadinessState.ACCEPTING_TRAFFIC);
        assertThat(applicationAvailability.getState(ReadinessState.class))
                .isEqualTo(ReadinessState.ACCEPTING_TRAFFIC);
    }

    @Test
    void shouldAvailabilityStateChange() throws Exception {
        // when
        AvailabilityChangeEvent.publish(webApplicationContext, LivenessState.BROKEN);

        // then
        assertThat(applicationAvailability.getLivenessState())
                .isEqualTo(LivenessState.BROKEN);
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health/liveness"))
                .andExpect(status().isServiceUnavailable())
                .andDo(print())
                .andExpect(jsonPath("$.status").value("DOWN"));
    }

}
