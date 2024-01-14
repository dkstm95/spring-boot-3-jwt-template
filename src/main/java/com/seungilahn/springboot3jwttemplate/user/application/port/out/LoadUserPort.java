package com.seungilahn.springboot3jwttemplate.user.application.port.out;

import com.seungilahn.springboot3jwttemplate.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {

    Optional<User> findUser(String email);

    User loadUser(String email);

}
