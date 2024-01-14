package com.seungilahn.springboot3jwttemplate.user.domain;

import com.seungilahn.springboot3jwttemplate.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true, nullable = false, length=30)
    private String email;

    @Getter
    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String phoneNumber;

    @Getter
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    private boolean enabled;

    @Builder(access = AccessLevel.PRIVATE)
    private User(String email, String name, String phoneNumber, String password, Role role, boolean enabled) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public static User create(String email, String name, String phoneNumber, String password, Role role) {
        return User.builder()
                .email(email)
                .name(name)
                .phoneNumber(phoneNumber)
                .password(password)
                .role(role)
                .enabled(true)
                .build();
    }

    public void changeUserInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void withdraw() {
        this.name = "withdraw";
        this.phoneNumber = "withdraw";
        this.password = "withdraw";
        this.enabled = false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }

}
