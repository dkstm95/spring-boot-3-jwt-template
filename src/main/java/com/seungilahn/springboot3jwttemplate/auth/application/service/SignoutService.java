package com.seungilahn.springboot3jwttemplate.auth.application.service;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignoutCommand;
import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignoutUseCase;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class SignoutService implements SignoutUseCase {

    private final LoadTokenPort loadTokenPort;

    @Override
    public void signout(SignoutCommand command) {
        Token token = loadTokenPort.loadToken(command.refreshToken());
        token.revoke();
    }

}
