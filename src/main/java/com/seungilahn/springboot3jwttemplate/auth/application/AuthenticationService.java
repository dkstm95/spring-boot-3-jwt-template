package com.seungilahn.springboot3jwttemplate.auth.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungilahn.springboot3jwttemplate.auth.domain.Token;
import com.seungilahn.springboot3jwttemplate.auth.domain.TokenRepository;
import com.seungilahn.springboot3jwttemplate.user.domain.User;
import com.seungilahn.springboot3jwttemplate.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthenticationResponse signup(SignupCommand command) {
        User newUser = User.create(
                command.email(),
                command.name(),
                command.phoneNumber(),
                passwordEncoder.encode(command.password()),
                command.role()
        );

        User savedUser = userRepository.save(newUser);

        String accessToken = jwtService.generateAccessToken(newUser);
        String refreshToken = jwtService.generateRefreshToken(newUser);

        createUserToken(savedUser, accessToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse signin(SigninCommand command) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(command.email(), command.password())
        );

        User user = (User) authenticate.getPrincipal();

        Token token = tokenRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Token not found."));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        token.refresh(refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse refreshToken(TokenRefreshCommand command) {

        String presentedRefreshToken = command.refreshToken();

        User user = getUserFromToken(presentedRefreshToken);

        Token token = tokenRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Token not found."));

        validateRefreshToken(presentedRefreshToken, token, user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        token.refresh(refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(presentedRefreshToken)
                .build();

    }

    private void validateRefreshToken(String presentedRefreshToken, Token token, User user) {
        if (!token.isSameRefreshToken(presentedRefreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
        if (!jwtService.isTokenValid(presentedRefreshToken, user)) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
    }

    private User getUserFromToken(String jwt) {
        String userEmail = Optional.ofNullable(jwtService.extractUsername(jwt))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));

        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    private void createUserToken(User user, String jwtToken) {
        Token token = Token.createBearerToken(jwtToken, user);
        tokenRepository.save(token);
    }

}
