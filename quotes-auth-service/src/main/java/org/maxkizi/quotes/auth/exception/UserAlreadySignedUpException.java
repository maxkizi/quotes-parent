package org.maxkizi.quotes.auth.exception;

import static org.maxkizi.quotes.auth.exception.Exceptions.USER_ALREADY_SIGNED_UP_MESSAGE;

public class UserAlreadySignedUpException extends RuntimeException {
    public UserAlreadySignedUpException() {
        super(USER_ALREADY_SIGNED_UP_MESSAGE);
    }
}
