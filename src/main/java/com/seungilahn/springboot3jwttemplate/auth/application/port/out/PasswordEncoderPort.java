package com.seungilahn.springboot3jwttemplate.auth.application.port.out;

public interface PasswordEncoderPort {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

}
