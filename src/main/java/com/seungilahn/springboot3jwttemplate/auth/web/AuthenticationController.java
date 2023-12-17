package com.seungilahn.springboot3jwttemplate.auth.web;

import com.seungilahn.springboot3jwttemplate.auth.application.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    ResponseEntity<AuthenticationResponse> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok(service.signup(request.toCommand()));
    }

    @PostMapping("/signin")
    ResponseEntity<AuthenticationResponse> signin(@RequestBody @Valid SigninRequest request) {
        return ResponseEntity.ok(service.signin(request.toCommand()));
    }

    @PostMapping("/refresh-token")
    ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody @Valid TokenRefreshRequest request) {
        return ResponseEntity.ok(service.refreshToken(request.toCommand()));
    }

}
