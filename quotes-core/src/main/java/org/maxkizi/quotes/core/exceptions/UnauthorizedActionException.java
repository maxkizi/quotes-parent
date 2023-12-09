package org.maxkizi.quotes.core.exceptions;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String userLogin) {
        super(String.format("User: %s cannot interact with this entity", userLogin));
    }
}
