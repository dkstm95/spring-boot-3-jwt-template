package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignoutCommand;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class SignoutRequest {

    private final @NotEmpty String refreshToken;

    public SignoutRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public SignoutCommand toCommand() {
        return new SignoutCommand(refreshToken);
    }

}
