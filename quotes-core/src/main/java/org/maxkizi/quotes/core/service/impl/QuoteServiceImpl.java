package org.maxkizi.quotes.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.exceptions.QuoteNotFoundException;
import org.maxkizi.quotes.core.model.Quote;
import org.maxkizi.quotes.core.repository.QuoteRepository;
import org.maxkizi.quotes.core.service.QuoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository repository;

    @Override
    public List<Quote> findByRatingType(RatingType ratingType, int count) {
        switch (ratingType) {
            case TOP:
                return repository.findQuotes(PageRequest.of(0, count, Sort.by("score").descending()));
            case WORSE:
                return repository.findQuotes(PageRequest.of(0, count, Sort.by("score").ascending()));
            default:
                throw new IllegalArgumentException("Undefined rating type");
        }
    }

    @Override
    public Quote getRandom() {
        return repository.findRandom();
    }

    @Override
    public Quote save(Quote quote) {
        return repository.save(quote);
    }

    @Override
    @Transactional
    public Quote updateContent(long id, String content) {
        Quote quote = repository.findById(id).orElseThrow(QuoteNotFoundException::new);
        quote.setContent(content);
        return quote;
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.findById(id).orElseThrow(QuoteNotFoundException::new);
        repository.deleteById(id);
    }

    @Override
    public Quote upvote(long id) {
        return null;
    }

    @Override
    public Quote downvote(long id) {
        return null;
    }
}
