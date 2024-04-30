package com.seungilahn.springboot3jwttemplate.auth.adapter.out.authentication;

import com.seungilahn.springboot3jwttemplate.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.springboot3jwttemplate.common.AuthenticationAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@AuthenticationAdapter
class PasswordEncoderPortAdapter implements PasswordEncoderPort {

    private final PasswordEncoder passwordEncoder;

    PasswordEncoderPortAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
