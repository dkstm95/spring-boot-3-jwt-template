package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

record SigninRequest(
        @NotEmpty @Email String email,
        @NotEmpty String password
) {

    public SigninCommand toCommand() {
        return new SigninCommand(email, password);
    }

}
