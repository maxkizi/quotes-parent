package org.maxkizi.quotes.core.exceptions;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException() {
        super(Exceptions.QUOTE_NOT_FOUND);
    }
}
