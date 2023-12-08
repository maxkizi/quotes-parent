package org.maxkizi.quotes.auth.exception;

import static org.maxkizi.quotes.auth.exception.Exceptions.USER_NOT_FOUND_MESSAGE;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND_MESSAGE);
    }
}
