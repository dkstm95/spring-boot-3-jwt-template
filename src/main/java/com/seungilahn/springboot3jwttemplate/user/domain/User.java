package com.seungilahn.springboot3jwttemplate.user.domain;

import com.seungilahn.springboot3jwttemplate.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String phoneNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    /**
     * Creates an {@link User} entity without an ID. Use to create a new entity that is not yet persisted.
     */
    public static User withoutId(String email, String name, String phoneNumber, String password, Role role, boolean enabled) {
        return new User(null, email, name, phoneNumber, password, role, enabled);
    }

    /**
     * Creates an {@link User} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static User withId(Long id, String email, String name, String phoneNumber, String password, Role role, boolean enabled) {
        return new User(id, email, name, phoneNumber, password, role, enabled);
    }

    public void changeUserInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void withdraw() {
        this.email = "withdrawn";
        this.name = "withdrawn";
        this.phoneNumber = "withdrawn";
        this.password = "withdrawn";
        this.enabled = false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }

}
