package com.seungilahn.springboot3jwttemplate.user.web;

import com.seungilahn.springboot3jwttemplate.common.LoginUser;
import com.seungilahn.springboot3jwttemplate.user.application.UserService;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('USER')")
class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity<?> changeUserInfo(@LoginUser User user, @RequestBody @Valid ChangeUserInfoRequest request) {
        userService.changeUserInfo(user, request.toCommand());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> withdraw(@LoginUser User user) {
        userService.withdraw(user);
        return ResponseEntity.ok().build();
    }

}
