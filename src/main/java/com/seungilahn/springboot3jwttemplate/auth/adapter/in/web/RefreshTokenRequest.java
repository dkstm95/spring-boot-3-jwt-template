package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenCommand;
import jakarta.validation.constraints.NotEmpty;

record RefreshTokenRequest(
        @NotEmpty String refreshToken
) {

    public RefreshTokenCommand toCommand() {
        return new RefreshTokenCommand(refreshToken);
    }

}
