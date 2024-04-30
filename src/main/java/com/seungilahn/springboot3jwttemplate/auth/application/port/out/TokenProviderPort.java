package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

import com.seungilahn.springboot3jwttemplate.auth.domain.AuthenticationTokens;

public interface TokenProviderPort {

    AuthenticationTokens generateAuthenticationTokens(String email);

    String extractEmail(String token);

    boolean isValidToken(String token, String email);

}
