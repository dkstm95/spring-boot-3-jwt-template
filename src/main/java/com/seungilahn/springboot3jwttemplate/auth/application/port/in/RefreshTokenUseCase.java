package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

public interface RefreshTokenUseCase {

    AuthenticationResponse tokenRefresh(RefreshTokenCommand command);

}
