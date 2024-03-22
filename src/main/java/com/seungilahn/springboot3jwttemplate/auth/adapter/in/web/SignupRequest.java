package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupCommand;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

record SignupRequest(
        @NotEmpty @Email String email,
        @NotEmpty String password,
        @NotEmpty String name,
        @NotEmpty String phoneNumber,
        Role role
) {

    public SignupCommand toCommand() {
        return new SignupCommand(email, password, name, phoneNumber, role);
    }

}
