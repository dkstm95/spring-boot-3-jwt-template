package com.seungilahn.springboot3jwttemplate.user.application;

import com.seungilahn.springboot3jwttemplate.user.domain.User;
import com.seungilahn.springboot3jwttemplate.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void changeUserInfo(User user, ChangeUserInfoCommand command) {
        user.changeUserInfo(command.name(), command.phoneNumber());
        userRepository.save(user);
    }

    @Transactional
    public void withdraw(User user) {
        user.withdraw();
        userRepository.save(user);
    }

}
