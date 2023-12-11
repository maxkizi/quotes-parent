package org.maxkizi.quotes.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SignupRequest {
    private String login;
    private String password;
    private String name;
}
