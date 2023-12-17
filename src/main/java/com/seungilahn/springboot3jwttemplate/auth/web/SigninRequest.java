package com.seungilahn.springboot3jwttemplate.auth.web;

import com.seungilahn.springboot3jwttemplate.auth.application.SigninCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SigninRequest(@NotEmpty @Email String email, @NotEmpty String password) {

    public SigninCommand toCommand() {
        return SigninCommand.builder()
                .email(email)
                .password(password)
                .build();
    }

}
