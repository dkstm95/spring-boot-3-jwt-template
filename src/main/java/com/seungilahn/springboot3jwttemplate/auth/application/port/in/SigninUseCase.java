package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

import com.seungilahn.springboot3jwttemplate.auth.application.service.AuthenticationResponse;

public interface SigninUseCase {

    AuthenticationResponse signin(SigninCommand command);

}
