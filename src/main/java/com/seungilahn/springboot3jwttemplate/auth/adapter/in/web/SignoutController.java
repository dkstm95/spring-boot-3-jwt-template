package com.seungilahn.springboot3jwttemplate.auth.adapter.in.web;

import com.seungilahn.springboot3jwttemplate.auth.application.port.out.LoadTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.application.port.out.SaveTokenPort;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.common.WebAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@RequiredArgsConstructor
@WebAdapter
class SignoutController implements LogoutHandler {

    private final LoadTokenPort loadTokenPort;
    private final SaveTokenPort saveTokenPort;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) return;

        String jwt = authHeader.substring(7);
        Token storedToken = loadTokenPort.loadToken(jwt);
        if (storedToken != null) {
            storedToken.revoke();
            saveTokenPort.saveToken(storedToken);
            SecurityContextHolder.clearContext();
        }
    }

}
