package com.seungilahn.springboot3jwttemplate.user.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import com.seungilahn.springboot3jwttemplate.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort private int port;
    @Autowired private WebApplicationContext context;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private UserRepository userRepository;


    private MockMvc mvc;

    private User user;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        user = userRepository.save(User.create(
                "test@email.com",
                "홍길동",
                "01012345678",
                passwordEncoder.encode("password"),
                Role.USER));
    }

    @AfterEach
    void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    @WithUserDetails(value = "test@email.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void user_정보_수정() throws Exception {
        // given
        String afterName = "김길동";
        String afterPhoneNumber = "01011112222";
        ChangeUserInfoRequest request = new ChangeUserInfoRequest(afterName, afterPhoneNumber);

        String url = "http://localhost:" + port + "/api/v1/users";

        // when
        mvc.perform(put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        assertThat(user.getName()).isEqualTo(afterName);
        assertThat(user.getPhoneNumber()).isEqualTo(afterPhoneNumber);
    }

    @Test
    @Transactional
    @WithUserDetails(value = "test@email.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void uesr_탈퇴() throws Exception {
        // given
        String url = "http://localhost:" + port + "/api/v1/users";

        // when
        mvc.perform(delete(url)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        assertThat(user.isEnabled()).isFalse();
    }

}
