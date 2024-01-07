package com.seungilahn.springboot3jwttemplate.auth.adapter.out.persistence;

import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.SaveTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.PersistenceAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class TokenPersistenceAdapter implements LoadTokenPort, SaveTokenPort {

    private final SpringDataTokenRepository tokenRepository;


    @Override
    public Token loadToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Token loadToken(Long userId) {
        return tokenRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

}
