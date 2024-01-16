package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public interface SignupUseCase {

    AuthenticationResponse signup(SignupCommand command);

}
