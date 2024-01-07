package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.RefreshTokenUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.ExtractUsernamePort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.GenerateTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.ValidTokenPort;
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
    private final GenerateTokenPort generateTokenPort;
    private final ExtractUsernamePort extractUsernamePort;
    private final ValidTokenPort validTokenPort;

    private final LoadUserPort loadUserPort;

    @Override
    public AuthenticationResponse tokenRefresh(RefreshTokenCommand command) {

        String presentedRefreshToken = command.refreshToken();

        User user = getUserFromToken(presentedRefreshToken);

        Token token = loadTokenPort.loadToken(user.getId());

        validateRefreshToken(presentedRefreshToken, token, user);

        String accessToken = generateTokenPort.generateAccessToken(user.getUsername());
        String refreshToken = generateTokenPort.generateRefreshToken(user.getUsername());

        token.refresh(refreshToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    private void validateRefreshToken(String presentedRefreshToken, Token token, User user) {
        if (!token.isSameRefreshToken(presentedRefreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
        if (!validTokenPort.isValidToken(presentedRefreshToken, user.getUsername())) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
    }

    private User getUserFromToken(String jwt) {
        String userEmail = Optional.ofNullable(extractUsernamePort.extractUsername(jwt))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));

        return loadUserPort.loadUser(userEmail);
    }

}
