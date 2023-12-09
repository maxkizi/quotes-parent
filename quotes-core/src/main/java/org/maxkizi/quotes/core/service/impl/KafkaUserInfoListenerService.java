package org.maxkizi.quotes.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.quotes.common.dto.UserInfo;
import org.maxkizi.quotes.core.model.User;
import org.maxkizi.quotes.core.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaUserInfoListenerService {
    private final UserRepository userRepository;

    @KafkaListener(topics = "${kafka.userInfoTopic}")
    public void listenUserInfo(@Payload UserInfo userInfo) {
        userRepository.findByLogin(userInfo.getLogin()).ifPresentOrElse(
                user -> log.info("User already exists: {}", user.getLogin()),
                () -> userRepository.save(User.builder().login(userInfo.getLogin()).build())
        );
    }
}
