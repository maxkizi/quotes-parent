package org.maxkizi.quotes.core.jwt;


import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.quotes.core.dto.PrincipalDto;
import org.maxkizi.quotes.core.model.User;
import org.maxkizi.quotes.core.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final String secretKey;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.error("Empty authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authorizationHeader.replace(TOKEN_PREFIX, "");

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken);

            Claims body = claimsJws.getBody();

            Optional<User> optUser = userRepository.findByLogin(body.getSubject());
            if (optUser.isEmpty()) {
                log.error("User: {} not found", body.getSubject());
                filterChain.doFilter(request, response);
                return;
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    new PrincipalDto(optUser.get().getId(), optUser.get().getLogin()),
                    null,
                    Collections.emptySet()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            log.error("Token not a valid: {}", jwtToken);
        }

        filterChain.doFilter(request, response);

    }
}
