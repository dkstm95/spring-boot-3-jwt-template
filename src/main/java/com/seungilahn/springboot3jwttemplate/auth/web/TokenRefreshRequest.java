package com.seungilahn.springboot3jwttemplate.auth.web;

import com.seungilahn.springboot3jwttemplate.auth.application.TokenRefreshCommand;
import jakarta.validation.constraints.NotEmpty;

public record TokenRefreshRequest(@NotEmpty String refreshToken) {

    public TokenRefreshCommand toCommand() {
        return TokenRefreshCommand.builder()
                .refreshToken(refreshToken)
                .build();
    }

}
