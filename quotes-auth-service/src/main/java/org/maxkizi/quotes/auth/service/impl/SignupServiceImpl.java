package org.maxkizi.quotes.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.quotes.auth.exception.UserAlreadySignedUpException;
import org.maxkizi.quotes.auth.model.User;
import org.maxkizi.quotes.auth.repository.UserRepository;
import org.maxkizi.quotes.auth.service.SignupService;
import org.maxkizi.quotes.common.dto.UserInfo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupServiceImpl implements SignupService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserInfo> kafkaTemplate;

    @Override
    @Transactional
    public void signup(User user) {
        userRepository.findByLogin(user.getLogin()).ifPresent(u -> {
            throw new UserAlreadySignedUpException();
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        kafkaTemplate.send("user", new UserInfo(user.getLogin()));
    }
}
