package com.seungilahn.springboot3jwttemplate.user.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.common.LoginUser;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import com.seungilahn.springboot3jwttemplate.user.application.port.in.ChangeUserInfoUseCase;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@WebAdapter
@PreAuthorize("hasRole('USER')")
class ChangeUserInfoController {

    private final ChangeUserInfoUseCase changeUserInfoUseCase;

    @PutMapping("/api/v1/users")
    public ResponseEntity<?> changeUserInfo(@LoginUser User user, @RequestBody @Valid ChangeUserInfoRequest request) {
        changeUserInfoUseCase.changeUserInfo(user, request.toCommand());
        return ResponseEntity.ok().build();
    }

}
