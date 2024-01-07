package com.seungilahn.springboot3jwttemplate.user.application.service;

import com.seungilahn.springboot3jwttemplate.common.UseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.in.WithdrawUserUseCase;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.SaveUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class WithdrawUserService implements WithdrawUserUseCase {

    private final SaveUserPort saveUserPort;

    @Override
    public void withdraw(User user) {
        user.withdraw();
        saveUserPort.saveUser(user);
    }

}
