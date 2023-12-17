package com.seungilahn.springboot3jwttemplate.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void 개인정보는_이름과_휴대번호만_수정가능하다() throws Exception {
        // given
        User sut = User.create(
                "test@email.com",
                "홍길동",
                "01012341234",
                "password",
                Role.USER
        );

        // when
        sut.changeUserInfo("김길동", "01043214321");

        // then
        assertThat(sut.getName()).isEqualTo("김길동");
        assertThat(sut.getPhoneNumber()).isEqualTo("01043214321");
    }

}