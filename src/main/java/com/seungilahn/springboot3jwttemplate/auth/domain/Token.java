package com.seungilahn.springboot3jwttemplate.auth.domain;

import com.seungilahn.springboot3jwttemplate.common.BaseTimeEntity;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Token extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TokenType tokenType;

    private boolean revoked;

    private boolean expired;

    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Creates an {@link Token} entity without an ID. Use to create a new entity that is not yet persisted.
     */
    public static Token withoutId(String token, User user, TokenType tokenType) {
        return new Token(null, token, tokenType, false, false, user);
    }

    /**
     * Creates an {@link Token} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static Token withId(Long id, String token, User user, TokenType tokenType) {
        return new Token(id, token, tokenType, false, false, user);
    }

    public void revoke() {
        this.revoked = true;
        this.expired = true;
    }

    public boolean isSameRefreshToken(String refreshToken) {
        return this.token.equals(refreshToken);
    }

    public void refresh(String refreshToken) {
        this.token = refreshToken;
        this.revoked = false;
        this.expired = false;
    }

    public boolean isValid() {
        return !this.revoked && !this.expired;
    }

}
