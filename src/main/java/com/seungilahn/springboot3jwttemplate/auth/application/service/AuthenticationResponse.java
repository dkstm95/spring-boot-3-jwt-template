package com.seungilahn.springboot3jwttemplate.auth.application.service;

public record AuthenticationResponse(String accessToken, String refreshToken) {
}
