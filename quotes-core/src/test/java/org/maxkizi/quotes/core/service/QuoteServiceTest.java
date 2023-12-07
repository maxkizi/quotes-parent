package org.maxkizi.quotes.core.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.quotes.core.BaseIntegrationTest;
import org.maxkizi.quotes.core.TestDataProvider;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.model.Quote;
import org.maxkizi.quotes.core.model.User;
import org.maxkizi.quotes.core.repository.QuoteRepository;
import org.maxkizi.quotes.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class QuoteServiceTest extends BaseIntegrationTest {
    private final QuoteService quoteService;
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;


    @Autowired
    QuoteServiceTest(QuoteService quoteService, QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteService = quoteService;
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    @AfterEach
    void deleteAll() {
        quoteRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findTopAndWorst() {
        User user = TestDataProvider.buildUser(1);
        userRepository.save(user);

        List<Quote> quotes = IntStream.range(0, 10).mapToObj(TestDataProvider::buildQuote)
                .peek(quote -> quote.setUser(user))
                .collect(Collectors.toList());
        quoteRepository.saveAll(quotes);

        List<Quote> topQuotes = quoteService.findByRatingType(RatingType.TOP, 10);
        Assertions.assertEquals(9, topQuotes.get(0).getScore());
        Assertions.assertEquals(0, topQuotes.get(9).getScore());
        Assertions.assertEquals(user.getLogin(), topQuotes.get(0).getUser().getLogin());

        List<Quote> worseQuotes = quoteService.findByRatingType(RatingType.WORSE, 10);
        Assertions.assertEquals(0, worseQuotes.get(0).getScore());
        Assertions.assertEquals(9, worseQuotes.get(9).getScore());
        Assertions.assertEquals(user.getLogin(), worseQuotes.get(5).getUser().getLogin());
    }

    @Test
    void findRandom() {
        List<Quote> quotes = IntStream.range(0, 10).mapToObj(TestDataProvider::buildQuote).collect(Collectors.toList());
        quoteRepository.saveAll(quotes);
        Assertions.assertNotNull(quoteService.getRandom());
    }

    @Test
    void updateContent() {
        String newContent = "new content";
        Long id = quoteService.save(TestDataProvider.buildQuote(1)).getId();

        quoteService.updateContent(id, newContent);
        Assertions.assertEquals(newContent, quoteRepository.findById(id).get().getContent());
        System.out.println();
    }
}