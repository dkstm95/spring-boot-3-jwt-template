package com.seungilahn.springboot3jwttemplate.auth.domain;

public record AuthenticationTokens(
        String accessToken,
        String refreshToken
) {
}
