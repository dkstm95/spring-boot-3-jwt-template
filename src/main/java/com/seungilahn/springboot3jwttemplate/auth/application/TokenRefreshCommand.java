package com.seungilahn.springboot3jwttemplate.auth.application;

import lombok.Builder;

@Builder
public record TokenRefreshCommand(String refreshToken) {
}
