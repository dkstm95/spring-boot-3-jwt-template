package com.seungilahn.springboot3jwttemplate.user.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.user.application.port.in.ChangeUserInfoCommand;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class ChangeUserInfoRequest {

    private final @NotEmpty String name;
    private final @NotEmpty String phoneNumber;

    public ChangeUserInfoRequest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ChangeUserInfoCommand toCommand() {
        return new ChangeUserInfoCommand(name, phoneNumber);
    }

}
