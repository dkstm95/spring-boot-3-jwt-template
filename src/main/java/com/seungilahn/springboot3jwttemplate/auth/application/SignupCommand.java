package com.seungilahn.springboot3jwttemplate.auth.application;

import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import lombok.Builder;

@Builder
public record SignupCommand(String email,
                            String password,
                            String name,
                            String phoneNumber,
                            Role role) {
}
