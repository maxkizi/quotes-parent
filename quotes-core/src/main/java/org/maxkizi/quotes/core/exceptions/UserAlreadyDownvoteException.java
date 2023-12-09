package org.maxkizi.quotes.core.exceptions;

import static org.maxkizi.quotes.core.exceptions.Exceptions.USER_ALREADY_DOWNVOTE;

public class UserAlreadyDownvoteException extends RuntimeException {
    public UserAlreadyDownvoteException() {
        super(USER_ALREADY_DOWNVOTE);
    }
}
