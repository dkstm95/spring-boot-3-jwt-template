package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

public interface GenerateTokenPort {

    String generateAccessToken(String username);

    String generateRefreshToken(String username);

}
