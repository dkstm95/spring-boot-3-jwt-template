package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.LoadUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
class RefreshTokenService implements RefreshTokenUseCase {

    private final LoadTokenPort loadTokenPort;
    private final TokenProviderPort tokenProviderPort;

    private final LoadUserPort loadUserPort;

    @Override
    public AuthenticationResponse tokenRefresh(RefreshTokenCommand command) {

        String presentedRefreshToken = command.refreshToken();

        User user = getUserFromToken(presentedRefreshToken);

        Token token = loadTokenPort.loadToken(user.getId());

        validateRefreshToken(presentedRefreshToken, token, user);

        String accessToken = tokenProviderPort.generateAccessToken(user.getEmail());
        String refreshToken = tokenProviderPort.generateRefreshToken(user.getEmail());

        token.refresh(refreshToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    private void validateRefreshToken(String presentedRefreshToken, Token token, User user) {
        if (!token.isSameRefreshToken(presentedRefreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
        if (!tokenProviderPort.isValidToken(presentedRefreshToken, user.getEmail())) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
        if (!token.isValid()) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
    }

    private User getUserFromToken(String jwt) {
        String userEmail = Optional.ofNullable(tokenProviderPort.extractEmail(jwt))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));

        return loadUserPort.loadUser(userEmail);
    }

}
