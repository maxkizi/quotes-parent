package org.maxkizi.quotes.core.service;

import org.maxkizi.quotes.core.dto.PrincipalDto;
import org.maxkizi.quotes.core.dto.projection.QuoteProjection;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.model.Quote;

import java.util.List;

public interface QuoteService {
    List<QuoteProjection> findByRatingType(RatingType ratingType, int count);

    QuoteProjection getRandom();

    Quote save(PrincipalDto userLogin, String quoteContent);

    Quote updateContent(long quoteId, String content, PrincipalDto principalDto);

    void delete(long quoteId, PrincipalDto principalDto);

    void upvote(PrincipalDto userLogin, long quoteId);

    void downvote(PrincipalDto userLogin, long quoteId);
}
