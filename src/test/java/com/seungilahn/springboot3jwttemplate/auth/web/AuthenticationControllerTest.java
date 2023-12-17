package com.seungilahn.springboot3jwttemplate.auth.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.auth.domain.TokenRepository;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import com.seungilahn.springboot3jwttemplate.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {

    @LocalServerPort private int port;
    @Autowired private WebApplicationContext context;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private TokenRepository tokenRepository;
    @Autowired private UserRepository userRepository;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    void 회원가입시_access_token_발급() throws Exception {
        // given
        String email = "test@email.com";
        SignupRequest request = new SignupRequest(
                email,
                "password",
                "name",
                "01012345678",
                Role.USER);

        String url = "http://localhost:" + port + "/api/v1/auth/signup";

        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        List<User> allUsers = userRepository.findAll();
        List<Token> allTokens = tokenRepository.findAll();
        assertThat(allUsers.get(0).getUsername()).isEqualTo(email);
        assertThat(allTokens.get(0).getUser()).isEqualTo(allUsers.get(0));
    }

    @Test
    @Transactional
    void 로그인시_새로운_token_발급() throws Exception {
        // given
        User user = User.create(
                "test@email.com"
                , "name"
                , "01012345678"
                , passwordEncoder.encode("password")
                , Role.USER);
        userRepository.save(user);

        Token existedToken = Token.createBearerToken("existedToken", user);
        String existedTokenStr = existedToken.getToken();
        tokenRepository.save(existedToken);

        SigninRequest request = new SigninRequest(user.getUsername(), "password");

        String url = "http://localhost:" + port + "/api/v1/auth/signin";

        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        List<Token> allTokens = tokenRepository.findAll();
        assertThat(allTokens.size()).isEqualTo(1);
        assertThat(allTokens.get(0).getUser()).isEqualTo(user);
        assertThat(allTokens.get(0).getToken()).isNotEqualTo(existedTokenStr);
    }

}