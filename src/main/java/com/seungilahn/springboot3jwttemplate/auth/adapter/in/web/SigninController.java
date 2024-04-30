package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninUseCase;
import com.seungilahn.springboot3jwttemplate.common.ApiResponse;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class SigninController {

    private final SigninUseCase useCase;

    SigninController(SigninUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/signin")
    ApiResponse<AuthenticationResponse> signin(@RequestBody @Valid SigninRequest request) {
        return ApiResponse.ok(useCase.signin(request.toCommand()));
    }

}
