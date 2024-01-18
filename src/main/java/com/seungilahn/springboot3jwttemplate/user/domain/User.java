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

    /**
     * Creates an {@link User} entity without an ID. Use to create a new entity that is not yet persisted.
     */
    public static User withoutId(String email, String name, String phoneNumber, String password, Role role) {
        return new User(null, email, name, phoneNumber, password, role, true);
    }

    /**
     * Creates an {@link User} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static User withId(Long id, String email, String name, String phoneNumber, String password, Role role) {
        return new User(id, email, name, phoneNumber, password, role, true);
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
