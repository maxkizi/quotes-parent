package org.maxkizi.quotes.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.maxkizi.quotes.common.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.maxkizi.quotes.auth.exception.Exceptions.USER_ALREADY_SIGNED_UP_MESSAGE;
import static org.maxkizi.quotes.common.exception.Exceptions.USER_NOT_FOUND_MESSAGE;

@RestControllerAdvice
@Slf4j
public class AuthServiceExceptionHandler {

    @ExceptionHandler(UserAlreadySignedUpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserAlreadySignedUpException() {
        log.error(USER_ALREADY_SIGNED_UP_MESSAGE);
        return USER_ALREADY_SIGNED_UP_MESSAGE;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleUserNotFoundException() {
        log.error(USER_NOT_FOUND_MESSAGE);
    }
}
