package org.maxkizi.quotes.core.dto.projection;

public interface QuoteProjection {
    Long getId();
    Long getScore();
    String getContent();
    String getLogin();
}
