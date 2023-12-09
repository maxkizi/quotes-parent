package org.maxkizi.quotes.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.quotes.auth.dto.AuthRequest;
import org.maxkizi.quotes.auth.dto.AuthResponse;
import org.maxkizi.quotes.auth.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtBuilder;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        log.info("Authentication attempt, login: {}", authRequest.getLogin());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        log.info("Authentication success, login: {}", authRequest.getLogin());
        return AuthResponse.builder()
                .accessToken(jwtBuilder.buildJwtToken(userDetailsService.loadUserByUsername(authRequest.getLogin())))
                .build();
    }
}
