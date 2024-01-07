package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

import com.seungilahn.springboot3jwttemplate.auth.domain.Token;

public interface SaveTokenPort {

    void saveToken(Token token);

}
