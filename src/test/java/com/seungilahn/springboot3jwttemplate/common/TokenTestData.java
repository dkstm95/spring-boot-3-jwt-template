package com.seungilahn.springboot3jwttemplate.common;

import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.auth.domain.TokenType;

public class TokenTestData {

    public static TokenTestDataBuilder defaultToken() {
        return new TokenTestDataBuilder()
                .withId(1L)
                .withUserId(1L)
                .withToken("token")
                .withTokenType(TokenType.BEARER)
                .withRevoked(false)
                .withExpired(false);
    }

    public static class TokenTestDataBuilder {
        private Long id;
        private Long userId;
        private String token;
        private TokenType tokenType;
        private boolean revoked;
        private boolean expired;

        public TokenTestDataBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TokenTestDataBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public TokenTestDataBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public TokenTestDataBuilder withTokenType(TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public TokenTestDataBuilder withRevoked(boolean revoked) {
            this.revoked = revoked;
            return this;
        }

        public TokenTestDataBuilder withExpired(boolean expired) {
            this.expired = expired;
            return this;
        }

        public Token build() {
            return Token.withId(id, userId, token, tokenType, revoked, expired);
        }
    }

}
