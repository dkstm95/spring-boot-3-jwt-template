package com.seungilahn.springboot3jwttemplate.user.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.common.ApiResponse;
import com.seungilahn.springboot3jwttemplate.common.LoginUser;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import com.seungilahn.springboot3jwttemplate.user.application.port.in.ChangeUserInfoUseCase;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@PreAuthorize("hasRole('USER')")
class ChangeUserInfoController {

    private final ChangeUserInfoUseCase changeUserInfoUseCase;

    ChangeUserInfoController(ChangeUserInfoUseCase changeUserInfoUseCase) {
        this.changeUserInfoUseCase = changeUserInfoUseCase;
    }

    @PutMapping("/api/v1/users")
    public ApiResponse<Void> changeUserInfo(@LoginUser User user, @RequestBody @Valid ChangeUserInfoRequest request) {
        changeUserInfoUseCase.changeUserInfo(user, request.toCommand());
        return ApiResponse.noContent();
    }

}
