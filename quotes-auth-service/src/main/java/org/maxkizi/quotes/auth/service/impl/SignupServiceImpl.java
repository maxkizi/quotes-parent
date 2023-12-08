package org.maxkizi.quotes.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.auth.model.User;
import org.maxkizi.quotes.auth.repository.UserRepository;
import org.maxkizi.quotes.auth.service.SignupService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void signup(User user) {
        userRepository.findByLogin(user.getLogin()).ifPresent(u -> {
            throw new RuntimeException();
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
