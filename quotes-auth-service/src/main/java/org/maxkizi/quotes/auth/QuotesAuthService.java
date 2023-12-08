package org.maxkizi.quotes.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class QuotesAuthService {
    public static void main(String[] args) {
        SpringApplication.run(QuotesAuthService.class);
    }
}
