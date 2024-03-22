package com.seungilahn.springboot3jwttemplate.user.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.user.application.port.in.ChangeUserInfoCommand;
import jakarta.validation.constraints.NotEmpty;

record ChangeUserInfoRequest(
        @NotEmpty String name,
        @NotEmpty String phoneNumber
) {

    public ChangeUserInfoCommand toCommand() {
        return new ChangeUserInfoCommand(name, phoneNumber);
    }

}
