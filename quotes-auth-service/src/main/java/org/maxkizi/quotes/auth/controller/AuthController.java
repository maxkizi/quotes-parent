package org.maxkizi.quotes.auth.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.auth.dto.AuthRequest;
import org.maxkizi.quotes.auth.dto.AuthResponse;
import org.maxkizi.quotes.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.maxkizi.quotes.auth.controller.Controllers.AUTHENTICATE;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(AUTHENTICATE)
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
