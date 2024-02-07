package com.seungilahn.springboot3jwttemplate.user.application.service;

import com.seungilahn.springboot3jwttemplate.common.UserTestData;
import com.seungilahn.springboot3jwttemplate.user.application.port.in.ChangeUserInfoCommand;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.SaveUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.Role;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.then;

class ChangeUserInfoServiceTest {

    private final SaveUserPort saveUserPort =
            Mockito.mock(SaveUserPort.class);

    private final ChangeUserInfoService changeUserInfoService =
            new ChangeUserInfoService(saveUserPort);

    @Test
    void 변경된_사용자_정보는_저장된다() {
        // given
        User user = UserTestData.defaultUser()
                .withId(1L)
                .withEmail("oldEmail")
                .withPassword("oldPassword")
                .withName("oldName")
                .withPhoneNumber("oldPhoneNumber")
                .withRole(Role.USER)
                .withEnabled(true)
                .build();

        ChangeUserInfoCommand command = new ChangeUserInfoCommand("seungilahn", "010-1234-5678");

        // when
        changeUserInfoService.changeUserInfo(user, command);

        // then
        then(saveUserPort).should().saveUser(user);
    }

}