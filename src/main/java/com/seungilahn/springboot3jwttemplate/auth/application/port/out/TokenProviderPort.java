package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

public interface TokenProviderPort {

    String generateAccessToken(String email);

    String generateRefreshToken(String email);

    String extractEmail(String token);

    boolean isValidToken(String token, String email);

}
