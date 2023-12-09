package org.maxkizi.quotes.core.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class QuoteExceptionHandler {
    @ExceptionHandler(UnauthorizedActionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedActionException(UnauthorizedActionException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }


    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleQuoteNotFoundException() {
        log.error(Exceptions.QUOTE_NOT_FOUND);
        return Exceptions.QUOTE_NOT_FOUND;
    }

    @ExceptionHandler(UserAlreadyUpvoteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAlreadyUpvoteException() {
        log.error(Exceptions.USER_ALREADY_UPVOTE);
        return Exceptions.USER_ALREADY_UPVOTE;
    }

    @ExceptionHandler(UserAlreadyDownvoteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAlreadyDownvoteException() {
        log.error(Exceptions.USER_ALREADY_DOWNVOTE);
        return Exceptions.USER_ALREADY_DOWNVOTE;
    }
}
