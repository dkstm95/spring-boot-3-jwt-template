package com.seungilahn.springboot3jwttemplate.auth.web;

import com.seungilahn.springboot3jwttemplate.auth.application.SignupCommand;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SignupRequest(@NotEmpty @Email String email,
                            @NotEmpty String password,
                            @NotEmpty String name,
                            @NotEmpty String phoneNumber,
                            Role role) {

    public SignupCommand toCommand() {
        return SignupCommand.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
    }

}
