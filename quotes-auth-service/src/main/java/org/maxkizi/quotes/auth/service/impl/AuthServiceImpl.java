package org.maxkizi.quotes.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.auth.dto.AuthRequest;
import org.maxkizi.quotes.auth.dto.AuthResponse;
import org.maxkizi.quotes.auth.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtBuilder;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        return AuthResponse.builder()
                .accessToken(jwtBuilder.buildJwtToken(userDetailsService.loadUserByUsername(authRequest.getLogin())))
                .build();
    }
}
