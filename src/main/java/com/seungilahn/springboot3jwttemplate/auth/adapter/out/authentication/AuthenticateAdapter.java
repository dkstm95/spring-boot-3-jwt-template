package com.seungilahn.springboot3jwttemplate.auth.adapter.out.authentication;

import com.seungilahn.springboot3jwttemplate.auth.application.port.out.AuthenticatePort;
import com.seungilahn.springboot3jwttemplate.common.AuthenticationAdapter;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@AuthenticationAdapter
class AuthenticateAdapter implements AuthenticatePort {

    private final AuthenticationManager authenticationManager;

    AuthenticateAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User authenticate(String email, String password) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return (User) authenticate.getPrincipal();

    }

}
