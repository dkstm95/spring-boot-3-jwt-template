package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignoutCommand;
import jakarta.validation.constraints.NotEmpty;

public record SignoutRequest(@NotEmpty String refreshToken) {

    public SignoutCommand toCommand() {
        return new SignoutCommand(refreshToken);
    }

}
