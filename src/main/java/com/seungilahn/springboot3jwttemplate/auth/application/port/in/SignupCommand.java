package com.seungilahn.springboot3jwttemplate.auth.application.port.in;

import com.seungilahn.springboot3jwttemplate.user.domain.Role;

public record SignupCommand(String email,
                            String password,
                            String name,
                            String phoneNumber,
                            Role role) {
}
