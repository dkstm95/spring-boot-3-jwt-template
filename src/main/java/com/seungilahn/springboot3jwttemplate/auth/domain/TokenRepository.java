package com.seungilahn.springboot3jwttemplate.auth.domain;

import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUser(User user);

    Optional<Token> findByToken(String token);

}
