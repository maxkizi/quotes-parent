package org.maxkizi.quotes.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.common.exception.UserNotFoundException;
import org.maxkizi.quotes.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }
}
