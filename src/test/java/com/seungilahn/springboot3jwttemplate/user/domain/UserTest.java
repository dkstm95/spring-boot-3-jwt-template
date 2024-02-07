package com.seungilahn.springboot3jwttemplate.user.domain;

import com.seungilahn.springboot3jwttemplate.common.UserTestData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void 유저는_이름과_전화번호를_변경할_수_있다() {
        // given
        User user = createExistingUser();

        // when
        user.changeUserInfo("newName", "newPhoneNumber");

        // then
        assertThat(user.getEmail()).isEqualTo("oldEmail");
        assertThat(user.getPassword()).isEqualTo("oldPassword");
        assertThat(user.getName()).isEqualTo("newName");
        assertThat(user.getPhoneNumber()).isEqualTo("newPhoneNumber");
        assertThat(user.getRole()).isEqualTo(Role.USER);
        assertThat(user.isEnabled()).isTrue();
    }

    @Test
    void 회원_탈퇴_시_유저는_비활성화된다() {
        // given
        User user = createExistingUser();

        // when
        user.withdraw();

        // then
        assertThat(user.getEmail()).isEqualTo("withdrawn");
        assertThat(user.getPassword()).isEqualTo("withdrawn");
        assertThat(user.getName()).isEqualTo("withdrawn");
        assertThat(user.getPhoneNumber()).isEqualTo("withdrawn");
        assertThat(user.getRole()).isEqualTo(Role.USER);
        assertThat(user.isEnabled()).isFalse();
    }

    private User createExistingUser() {
        return UserTestData.defaultUser()
                .withId(1L)
                .withEmail("oldEmail")
                .withPassword("oldPassword")
                .withName("oldName")
                .withPhoneNumber("oldPhoneNumber")
                .withRole(Role.USER)
                .withEnabled(true)
                .build();
    }

}