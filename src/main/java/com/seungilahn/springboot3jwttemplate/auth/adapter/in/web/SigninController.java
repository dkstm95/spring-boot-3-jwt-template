package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
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
class SigninController {

    private final SigninUseCase useCase;

    @PostMapping("/api/v1/auth/signin")
    ResponseEntity<AuthenticationResponse> signin(@RequestBody @Valid SigninRequest request) {
        return ResponseEntity.ok(useCase.signin(request.toCommand()));
    }

}
