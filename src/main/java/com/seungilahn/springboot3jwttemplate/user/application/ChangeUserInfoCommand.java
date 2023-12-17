package com.seungilahn.springboot3jwttemplate.user.application;

import lombok.Builder;

@Builder
public record ChangeUserInfoCommand(String name, String phoneNumber) {
}