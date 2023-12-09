package org.maxkizi.quotes.core.exceptions;

import static org.maxkizi.quotes.core.exceptions.Exceptions.USER_ALREADY_UPVOTE;

public class UserAlreadyUpvoteException extends RuntimeException {
    public UserAlreadyUpvoteException() {
        super(USER_ALREADY_UPVOTE);
    }
}
