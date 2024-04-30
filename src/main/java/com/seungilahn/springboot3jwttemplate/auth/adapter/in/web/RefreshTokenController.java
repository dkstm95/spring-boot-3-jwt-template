package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenUseCase;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class RefreshTokenController {

    private final RefreshTokenUseCase useCase;

    RefreshTokenController(RefreshTokenUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/refresh-token")
    ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(useCase.tokenRefresh(request.toCommand()));
    }

}
