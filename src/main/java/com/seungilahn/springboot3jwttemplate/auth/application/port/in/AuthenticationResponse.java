package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public record AuthenticationResponse(String accessToken, String refreshToken) {
}
