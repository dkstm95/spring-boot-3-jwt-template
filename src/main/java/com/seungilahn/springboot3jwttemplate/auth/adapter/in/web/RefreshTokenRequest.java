package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenCommand;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class RefreshTokenRequest {

    private final @NotEmpty String refreshToken;

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public RefreshTokenCommand toCommand() {
        return new RefreshTokenCommand(refreshToken);
    }

}
