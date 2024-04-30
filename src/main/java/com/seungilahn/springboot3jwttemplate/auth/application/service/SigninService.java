package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.AuthenticatePort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.AuthenticationTokens;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class SigninService implements SigninUseCase {

    private final LoadTokenPort loadTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final AuthenticatePort authenticatePort;

    SigninService(LoadTokenPort loadTokenPort, TokenProviderPort tokenProviderPort, AuthenticatePort authenticatePort) {
        this.loadTokenPort = loadTokenPort;
        this.tokenProviderPort = tokenProviderPort;
        this.authenticatePort = authenticatePort;
    }

    @Override
    public AuthenticationResponse signin(SigninCommand command) {

        User user = authenticatePort.authenticate(command.email(), command.password());

        Token token = loadTokenPort.loadToken(user.getId());

        AuthenticationTokens tokens = tokenProviderPort.generateAuthenticationTokens(user.getEmail());

        token.refresh(tokens.refreshToken());

        return new AuthenticationResponse(tokens.accessToken(), tokens.refreshToken());
    }

}
