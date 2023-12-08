package org.maxkizi.quotes.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.exceptions.QuoteNotFoundException;
import org.maxkizi.quotes.core.model.Quote;
import org.maxkizi.quotes.core.model.User;
import org.maxkizi.quotes.core.repository.QuoteRepository;
import org.maxkizi.quotes.core.repository.UserRepository;
import org.maxkizi.quotes.core.service.QuoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    @Override
    public List<Quote> findByRatingType(RatingType ratingType, int count) {
        switch (ratingType) {
            case TOP:
                return quoteRepository.findQuotes(PageRequest.of(0, count, Sort.by("score").descending()));
            case WORSE:
                return quoteRepository.findQuotes(PageRequest.of(0, count, Sort.by("score").ascending()));
            default:
                throw new IllegalArgumentException("Undefined rating type");
        }
    }

    @Override
    public Quote getRandom() {
        return quoteRepository.findRandom();
    }

    @Override
    @Transactional
    public Quote save(String userLogin, String quoteContent) {
        Optional<User> optUser = userRepository.findByLogin(userLogin);
        Quote newQuote = Quote.builder()
                .content(quoteContent)
                .score(0L)
                .build();
        if (optUser.isPresent()) {
            newQuote.setUser(optUser.get());
        } else {
            newQuote.setUser(userRepository.save(User.builder().login(userLogin).build()));
        }
        quoteRepository.save(newQuote);
        return newQuote;
    }

    @Override
    @Transactional
    public Quote updateContent(long id, String content) {
        Quote quote = quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
        quote.setContent(content);
        return quote;
    }

    @Override
    @Transactional
    public void delete(long id) {
        quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
        quoteRepository.deleteById(id);
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
