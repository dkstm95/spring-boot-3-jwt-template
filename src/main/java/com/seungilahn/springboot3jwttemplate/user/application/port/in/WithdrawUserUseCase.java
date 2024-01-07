package com.seungilahn.springboot3jwttemplate.user.application.port.in;

import com.seungilahn.springboot3jwttemplate.user.domain.User;

public interface WithdrawUserUseCase {

    void withdraw(User user);

}
