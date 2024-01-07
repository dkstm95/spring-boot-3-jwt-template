package com.seungilahn.springboot3jwttemplate.user.application.port.out;

import com.seungilahn.springboot3jwttemplate.user.domain.User;

public interface LoadUserPort {

    User loadUser(String email);

}
