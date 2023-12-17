package com.seungilahn.springboot3jwttemplate.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void 사용자_email은_유일해야한다() {
        // given
        User existedUser = User.create("existed@email.com", "name", "01012341234", "password", Role.USER);
        userRepository.save(existedUser);

        // when
        User newUser = User.create("existed@email.com", "name2", "01034563456", "password2", Role.USER);

        // then
        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(newUser));
    }

}