package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.AuthenticationTokens;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.LoadUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@Transactional
class RefreshTokenService implements RefreshTokenUseCase {

    private final LoadTokenPort loadTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserPort loadUserPort;

    RefreshTokenService(LoadTokenPort loadTokenPort, TokenProviderPort tokenProviderPort, LoadUserPort loadUserPort) {
        this.loadTokenPort = loadTokenPort;
        this.tokenProviderPort = tokenProviderPort;
        this.loadUserPort = loadUserPort;
    }

    @Override
    public AuthenticationResponse tokenRefresh(RefreshTokenCommand command) {

        User user = loadUserFromToken(command.refreshToken());

        Token token = loadTokenPort.loadToken(user.getId());

        validateRefreshToken(command.refreshToken(), token, user);

        AuthenticationTokens tokens = tokenProviderPort.generateAuthenticationTokens(user.getEmail());

        token.refresh(tokens.refreshToken());

        return new AuthenticationResponse(tokens.accessToken(), tokens.refreshToken());
    }

    private User loadUserFromToken(String jwt) {
        String userEmail = Optional.ofNullable(tokenProviderPort.extractEmail(jwt))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));
        return loadUserPort.loadUser(userEmail);
    }

    private void validateRefreshToken(String oldRefreshToken, Token token, User user) {
        if (!token.isSameRefreshToken(oldRefreshToken) ||
                !token.isValid() ||
                !tokenProviderPort.isValidToken(oldRefreshToken, user.getEmail())) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
    }

}
