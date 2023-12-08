package org.maxkizi.quotes.core.service;

import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.model.Quote;

import java.util.List;

public interface QuoteService {
    List<Quote> findByRatingType(RatingType ratingType, int count);

    Quote getRandom();

    Quote save(String userLogin, String quoteContent);

    Quote updateContent(long id, String content);

    void delete(long id);

    Quote upvote(long id);

    Quote downvote(long id);
}
