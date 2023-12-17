package com.seungilahn.springboot3jwttemplate.auth.application;

import lombok.Builder;

@Builder
public record SigninCommand(String email, String password) {
}
