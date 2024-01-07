package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.service.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@WebAdapter
@RestController
class SignupController {

    private final SignupUseCase useCase;

    @PostMapping("/api/v1/auth/signup")
    ResponseEntity<AuthenticationResponse> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok(useCase.signup(request.toCommand()));
    }

}
