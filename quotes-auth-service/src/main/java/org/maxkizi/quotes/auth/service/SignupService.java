package org.maxkizi.quotes.auth.service;

import org.maxkizi.quotes.auth.dto.SignupRequest;
import org.maxkizi.quotes.auth.model.User;

public interface SignupService {
    void signup(User user);
}
