package com.seungilahn.springboot3jwttemplate.auth.domain;

import com.seungilahn.springboot3jwttemplate.common.BaseTimeEntity;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder(access = AccessLevel.PRIVATE)
    private Token(String token, TokenType tokenType, boolean revoked, boolean expired, User user) {
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
        this.user = user;
    }

    public static Token createBearerToken(String token, User user) {
        return Token.builder()
                .token(token)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .user(user)
                .build();
    }

    public boolean isValid() {
        return !this.revoked && !this.expired;
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
}
