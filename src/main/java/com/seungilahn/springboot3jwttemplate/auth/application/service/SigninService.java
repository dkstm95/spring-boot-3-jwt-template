package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SigninUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.GenerateTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class SigninService implements SigninUseCase {

    private final LoadTokenPort loadTokenPort;
    private final GenerateTokenPort generateTokenPort;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signin(SigninCommand command) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(command.email(), command.password())
        );

        User user = (User) authenticate.getPrincipal();

        Token token = loadTokenPort.loadToken(user.getId());

        String accessToken = generateTokenPort.generateAccessToken(user.getUsername());
        String refreshToken = generateTokenPort.generateRefreshToken(user.getUsername());

        token.refresh(refreshToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

}
