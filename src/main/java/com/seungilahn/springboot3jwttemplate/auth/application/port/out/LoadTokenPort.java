package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

import com.seungilahn.springboot3jwttemplate.auth.domain.Token;

public interface LoadTokenPort {

    Token loadToken(String token);

    Token loadToken(Long userId);

}
