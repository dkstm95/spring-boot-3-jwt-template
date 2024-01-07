package com.seungilahn.springboot3jwttemplate.user.application.port.in;

public record ChangeUserInfoCommand(String name, String phoneNumber) {

    public ChangeUserInfoCommand {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name must not be null or empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("phoneNumber must not be null or empty");
        }
    }

}