package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public interface SigninUseCase {

    AuthenticationResponse signin(SigninCommand command);

}
