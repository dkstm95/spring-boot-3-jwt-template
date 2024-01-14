package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public interface SignoutUseCase {

    void signout(SignoutCommand command);

}
