package com.seungilahn.springboot3jwttemplate.auth.application;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String accessToken, String refreshToken) {
}
