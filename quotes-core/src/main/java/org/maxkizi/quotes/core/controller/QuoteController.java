package org.maxkizi.quotes.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.core.dto.QuoteDto;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.mapper.QuoteMapper;
import org.maxkizi.quotes.core.service.QuoteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.maxkizi.quotes.core.controller.Controllers.QUOTES;
import static org.maxkizi.quotes.core.controller.Controllers.QUOTE_BY_ID;
import static org.maxkizi.quotes.core.controller.Controllers.QUOTE_DOWNVOTE;
import static org.maxkizi.quotes.core.controller.Controllers.QUOTE_RANDOM;
import static org.maxkizi.quotes.core.controller.Controllers.QUOTE_UPVOTE;

@RestController
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;


    @GetMapping(QUOTES)
    public List<QuoteDto> getQuotes(@RequestParam(name = "ratingType") RatingType ratingType,
                                    @RequestParam(name = "count", defaultValue = "10", required = false) int count) {
        return quoteService.findByRatingType(ratingType, count).stream().map(quoteMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(QUOTE_RANDOM)
    public QuoteDto getRandomQuote() {
        return quoteMapper.toDto(quoteService.getRandom());
    }

    @PatchMapping(QUOTE_BY_ID)
    public QuoteDto updateQuote(@PathVariable(name = "id") long id,
                                @RequestBody String content) {
        return quoteMapper.toDto(quoteService.updateContent(id, content));
    }

    @DeleteMapping(QUOTE_BY_ID)
    public void deleteQuote(@PathVariable(name = "id") long id) {
        quoteService.delete(id);
    }

    @PatchMapping(QUOTE_UPVOTE)
    public QuoteDto upvoteQuote(@PathVariable(name = "id") long id) {
        return quoteMapper.toDto(quoteService.upvote(id));
    }

    @PatchMapping(QUOTE_DOWNVOTE)
    public QuoteDto downQuote(@PathVariable(name = "id") long id) {
        return quoteMapper.toDto(quoteService.downvote(id));
    }
}
