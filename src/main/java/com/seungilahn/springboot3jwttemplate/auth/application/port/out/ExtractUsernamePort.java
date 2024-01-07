package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

public interface ExtractUsernamePort {

    String extractUsername(String token);

}
