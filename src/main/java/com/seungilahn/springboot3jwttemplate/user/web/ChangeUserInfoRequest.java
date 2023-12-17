package com.seungilahn.springboot3jwttemplate.user.web;

import com.seungilahn.springboot3jwttemplate.user.application.ChangeUserInfoCommand;
import jakarta.validation.constraints.NotEmpty;

public record ChangeUserInfoRequest(
        @NotEmpty String name,
        @NotEmpty String phoneNumber) {

    public ChangeUserInfoCommand toCommand() {
        return ChangeUserInfoCommand.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }

}
