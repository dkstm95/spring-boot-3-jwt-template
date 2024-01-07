package com.seungilahn.springboot3jwttemplate.user.adapter.out.persistence;

import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
