package org.maxkizi.quotes.auth.service;

import org.maxkizi.quotes.auth.dto.AuthRequest;
import org.maxkizi.quotes.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
