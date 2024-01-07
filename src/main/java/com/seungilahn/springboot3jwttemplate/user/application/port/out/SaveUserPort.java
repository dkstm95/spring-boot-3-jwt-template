package com.seungilahn.springboot3jwttemplate.user.application.port.out;

import com.seungilahn.springboot3jwttemplate.user.domain.User;

public interface SaveUserPort {

    User saveUser(User user);

}
