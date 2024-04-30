package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

import com.seungilahn.springboot3jwttemplate.auth.domain.AuthenticationTokens;

public interface RefreshTokenUseCase {

    AuthenticationTokens tokenRefresh(RefreshTokenCommand command);

}
