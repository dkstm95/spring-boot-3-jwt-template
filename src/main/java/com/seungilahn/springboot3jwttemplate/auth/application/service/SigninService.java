package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.AuthenticatePort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class SigninService implements SigninUseCase {

    private final LoadTokenPort loadTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final AuthenticatePort authenticatePort;

    @Override
    public AuthenticationResponse signin(SigninCommand command) {

        User user = authenticatePort.authenticate(command.email(), command.password());

        Token token = loadTokenPort.loadToken(user.getId());

        String accessToken = tokenProviderPort.generateAccessToken(user.getEmail());
        String refreshToken = tokenProviderPort.generateRefreshToken(user.getEmail());

        token.refresh(refreshToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

}
