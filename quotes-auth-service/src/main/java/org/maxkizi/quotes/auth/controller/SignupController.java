package org.maxkizi.quotes.auth.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.auth.dto.SignupRequest;
import org.maxkizi.quotes.auth.mapper.UserMapper;
import org.maxkizi.quotes.auth.service.SignupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.maxkizi.quotes.auth.controller.Controllers.SIGNUP;

@RestController
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;
    private final UserMapper userMapper;


    @PostMapping(SIGNUP)
    public void signup(@RequestBody SignupRequest signupRequest) {
        signupService.signup(userMapper.toEntity(signupRequest));
    }

}
