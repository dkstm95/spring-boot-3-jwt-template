package com.seungilahn.springboot3jwttemplate.user.adapter.out.persistence;

import com.seungilahn.springboot3jwttemplate.common.PersistenceAdapter;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.LoadUserPort;
import com.seungilahn.springboot3jwttemplate.user.application.port.out.SaveUserPort;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {

    private final SpringDataUserRepository userRepository;

    @Override
    public User loadUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
