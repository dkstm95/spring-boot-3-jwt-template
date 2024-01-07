package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

import com.seungilahn.springboot3jwttemplate.auth.application.service.AuthenticationResponse;

public interface RefreshTokenUseCase {

    AuthenticationResponse tokenRefresh(RefreshTokenCommand command);

}
