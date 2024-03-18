package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.in.SignupCommand;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class SignupRequest {

    private final @NotEmpty @Email String email;
    private final @NotEmpty String password;
    private final @NotEmpty String name;
    private final @NotEmpty String phoneNumber;
    private final Role role;

    public SignupRequest(String email, String password, String name, String phoneNumber, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public SignupCommand toCommand() {
        return new SignupCommand(email, password, name, phoneNumber, role);
    }

}
