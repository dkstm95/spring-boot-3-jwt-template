package com.seungilahn.springboot3jwttemplate.auth.domain;

import com.seungilahn.springboot3jwttemplate.common.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
public class Token extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean revoked;

    private boolean expired;

    protected Token() { }

    private Token(Long id, Long userId, String token, TokenType tokenType, boolean revoked, boolean expired) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
    }

    /**
     * Creates an {@link Token} entity without an ID. Use to create a new entity that is not yet persisted.
     */
    public static Token withoutId(Long userId, String token, TokenType tokenType, boolean revoked, boolean expired) {
        return new Token(null, userId, token, tokenType, revoked, expired);
    }

    /**
     * Creates an {@link Token} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static Token withId(Long id, Long userId, String token, TokenType tokenType, boolean revoked, boolean expired) {
        return new Token(id, userId, token, tokenType, revoked, expired);
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
