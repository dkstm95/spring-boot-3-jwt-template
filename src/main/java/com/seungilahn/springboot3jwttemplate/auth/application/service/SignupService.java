package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.SaveTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.auth.domain.TokenType;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.LoadUserPort;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.SaveUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class SignupService implements SignupUseCase {

    private final SaveTokenPort saveTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;

    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public AuthenticationResponse signup(SignupCommand command) {

        validateEmail(command.email());

        User newUser = User.withoutId(
                command.email(),
                command.name(),
                command.phoneNumber(),
                passwordEncoderPort.encode(command.password()),
                command.role(),
                true
        );
        User savedUser = saveUserPort.saveUser(newUser);

        String accessToken = tokenProviderPort.generateAccessToken(newUser.getEmail());
        String refreshToken = tokenProviderPort.generateRefreshToken(newUser.getEmail());
        Token token = Token.withoutId(accessToken, savedUser, TokenType.BEARER);
        saveTokenPort.saveToken(token);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    private void validateEmail(String email) {
        if (loadUserPort.findUser(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

}
