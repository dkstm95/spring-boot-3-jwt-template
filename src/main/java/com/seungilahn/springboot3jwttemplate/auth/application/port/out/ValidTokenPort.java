package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

public interface ValidTokenPort {

    boolean isValidToken(String token, String username);

}
