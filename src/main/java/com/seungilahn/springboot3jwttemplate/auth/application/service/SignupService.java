package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.GenerateTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.SaveTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.SaveUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class SignupService implements SignupUseCase {

    private final SaveTokenPort saveTokenPort;
    private final GenerateTokenPort generateTokenPort;

    private final SaveUserPort saveUserPort;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signup(SignupCommand command) {

        User newUser = User.create(
                command.email(),
                command.name(),
                command.phoneNumber(),
                passwordEncoder.encode(command.password()),
                command.role()
        );
        User savedUser = saveUserPort.saveUser(newUser);

        String accessToken = generateTokenPort.generateAccessToken(newUser.getUsername());
        String refreshToken = generateTokenPort.generateRefreshToken(newUser.getUsername());

        Token token = Token.createBearerToken(accessToken, savedUser);
        saveTokenPort.saveToken(token);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

}
