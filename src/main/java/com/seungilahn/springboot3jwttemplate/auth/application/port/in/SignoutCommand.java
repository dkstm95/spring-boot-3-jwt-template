package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public record SignoutCommand(String refreshToken) {

    public SignoutCommand {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new IllegalArgumentException("refreshToken must not be null or blank");
        }
    }

}
