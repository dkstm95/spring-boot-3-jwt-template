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

    public static Token createBearerToken(String token, User user) {
        return new Token(null, token, TokenType.BEARER, false, false, user);
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
