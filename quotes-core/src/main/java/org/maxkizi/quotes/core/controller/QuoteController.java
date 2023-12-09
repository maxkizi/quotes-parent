package org.maxkizi.quotes.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.quotes.core.dto.PrincipalDto;
import org.maxkizi.quotes.core.dto.response.QuoteDto;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.mapper.QuoteMapper;
import org.maxkizi.quotes.core.service.QuoteService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(QUOTES)
    public QuoteDto create(@RequestBody String quoteContent) {
        PrincipalDto principal = (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return quoteMapper.toDto(quoteService.save(principal, quoteContent));
    }

    @GetMapping(QUOTE_RANDOM)
    public QuoteDto getRandomQuote() {
        return quoteMapper.toDto(quoteService.getRandom());
    }

    @PatchMapping(QUOTE_BY_ID)
    public QuoteDto updateQuote(@PathVariable(name = "id") long quoteId,
                                @RequestBody String content) {
        PrincipalDto principal = (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return quoteMapper.toDto(quoteService.updateContent(quoteId, content, principal));
    }

    @DeleteMapping(QUOTE_BY_ID)
    public void deleteQuote(@PathVariable(name = "id") long quoteId) {
        PrincipalDto principal = (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quoteService.delete(quoteId, principal);
    }

    @PatchMapping(QUOTE_UPVOTE)
    public void upvoteQuote(@PathVariable(name = "id") long id) {
        PrincipalDto principal = (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quoteService.upvote(principal, id);
    }

    @PatchMapping(QUOTE_DOWNVOTE)
    public void downQuote(@PathVariable(name = "id") long id) {
        PrincipalDto principal = (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quoteService.downvote(principal, id);
    }
}
