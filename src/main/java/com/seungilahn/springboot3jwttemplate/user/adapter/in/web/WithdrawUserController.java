package com.seungilahn.springboot3jwttemplate.user.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.common.LoginUser;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import com.seungilahn.springboot3jwttemplate.user.application.port.in.WithdrawUserUseCase;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@WebAdapter
@PreAuthorize("hasRole('USER')")
class WithdrawUserController {

    private final WithdrawUserUseCase withdrawUserUseCase;

    @DeleteMapping("/api/v1/users")
    public ResponseEntity<?> withdraw(@LoginUser User user) {
        withdrawUserUseCase.withdraw(user);
        return ResponseEntity.ok().build();
    }

}
