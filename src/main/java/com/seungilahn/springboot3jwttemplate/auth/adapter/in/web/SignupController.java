package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupUseCase;
import com.seungilahn.springboot3jwttemplate.auth.domain.AuthenticationTokens;
import com.seungilahn.springboot3jwttemplate.common.ApiResponse;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class SignupController {

    private final SignupUseCase useCase;

    SignupController(SignupUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/signup")
    ApiResponse<AuthenticationTokens> signup(@RequestBody @Valid SignupRequest request) {
        return ApiResponse.ok(useCase.signup(request.toCommand()));
    }

}
