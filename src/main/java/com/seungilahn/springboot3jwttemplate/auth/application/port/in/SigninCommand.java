package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public record SigninCommand(String email, String password) {

    public SigninCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email must not be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password must not be null or blank");
        }
    }

}
