package com.seungilahn.springboot3jwttemplate.user.application.port.in;

import com.seungilahn.springboot3jwttemplate.user.domain.User;

public interface ChangeUserInfoUseCase {

    void changeUserInfo(User user, ChangeUserInfoCommand command);

}
