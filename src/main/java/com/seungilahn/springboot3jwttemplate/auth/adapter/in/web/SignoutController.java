package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignoutUseCase;
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
class SignoutController {

    private final SignoutUseCase useCase;

    @PostMapping("/api/v1/auth/signout")
    ResponseEntity<Void> signout(@RequestBody @Valid SignoutRequest request) {
        useCase.signout(request.toCommand());
        return ResponseEntity.noContent().build();
    }

}
