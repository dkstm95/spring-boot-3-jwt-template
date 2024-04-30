package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.LoadUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SignupController.class)
@AutoConfigureMockMvc(addFilters = false)
class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoadUserPort loadUserPort;
    @MockBean
    private TokenProviderPort tokenProviderPort;

    @MockBean
    private SignupUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSignup() throws Exception {
        SignupRequest signupRequest = new SignupRequest(
                "test@example.com",
                "password",
                "Test User",
                "01012345678",
                Role.USER);

        mockMvc.perform(post("/api/v1/auth/signup")
                                .content(objectMapper.writeValueAsString(signupRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(useCase).should()
                .signup(eq(signupRequest.toCommand()));
    }

}