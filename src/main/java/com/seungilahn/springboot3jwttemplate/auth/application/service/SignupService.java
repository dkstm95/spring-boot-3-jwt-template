package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.SaveTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.TokenProviderPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.AuthenticationTokens;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.auth.domain.TokenType;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.LoadUserPort;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.SaveUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class SignupService implements SignupUseCase {

    private final SaveTokenPort saveTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final PasswordEncoderPort passwordEncoderPort;

    SignupService(SaveTokenPort saveTokenPort, TokenProviderPort tokenProviderPort, LoadUserPort loadUserPort, SaveUserPort saveUserPort, PasswordEncoderPort passwordEncoderPort) {
        this.saveTokenPort = saveTokenPort;
        this.tokenProviderPort = tokenProviderPort;
        this.loadUserPort = loadUserPort;
        this.saveUserPort = saveUserPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthenticationResponse signup(SignupCommand command) {

        validateEmailIsNotInUse(command);

        User savedUser = createUserAndSave(command);

        AuthenticationTokens tokens = generateTokensAndSave(command, savedUser);

        return new AuthenticationResponse(tokens.accessToken(), tokens.refreshToken());
    }

    private void validateEmailIsNotInUse(SignupCommand command) {
        if (loadUserPort.findUser(command.email()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    private User createUserAndSave(SignupCommand command) {
        User newUser = User.withoutId(
                command.email(),
                command.name(),
                command.phoneNumber(),
                passwordEncoderPort.encode(command.password()),
                command.role(),
                true
        );
        return saveUserPort.saveUser(newUser);
    }

    private AuthenticationTokens generateTokensAndSave(SignupCommand command, User savedUser) {
        AuthenticationTokens tokens = tokenProviderPort.generateAuthenticationTokens(command.email());
        Token token = Token.withoutId(savedUser.getId(), tokens.accessToken(), TokenType.BEARER, false, false);
        saveTokenPort.saveToken(token);
        return tokens;
    }

}
