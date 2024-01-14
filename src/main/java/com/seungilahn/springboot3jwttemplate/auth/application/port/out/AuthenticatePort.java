package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

import com.seungilahn.springboot3jwttemplate.user.domain.User;

public interface AuthenticatePort {

    User authenticate(String email, String password);

}
