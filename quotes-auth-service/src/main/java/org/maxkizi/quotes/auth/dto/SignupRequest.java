package org.maxkizi.quotes.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {
    private String login;
    private String password;
    private String name;

}
