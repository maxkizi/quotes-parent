package org.maxkizi.quotes.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.quotes.core.dto.PrincipalDto;
import org.maxkizi.quotes.core.dto.projection.QuoteProjection;
import org.maxkizi.quotes.core.enumeration.RatingType;
import org.maxkizi.quotes.core.enumeration.VoteStatus;
import org.maxkizi.quotes.core.exceptions.QuoteNotFoundException;
import org.maxkizi.quotes.core.exceptions.UnauthorizedActionException;
import org.maxkizi.quotes.core.exceptions.UserAlreadyDownvoteException;
import org.maxkizi.quotes.core.exceptions.UserAlreadyUpvoteException;
import org.maxkizi.quotes.core.model.Quote;
import org.maxkizi.quotes.core.model.Vote;
import org.maxkizi.quotes.core.model.VoteId;
import org.maxkizi.quotes.core.repository.LikeRepository;
import org.maxkizi.quotes.core.repository.QuoteRepository;
import org.maxkizi.quotes.core.repository.UserRepository;
import org.maxkizi.quotes.core.service.QuoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final LikeRepository voteRepository;

    @Override
    public List<QuoteProjection> findByRatingType(RatingType ratingType, int count) {
        switch (ratingType) {
            case TOP:
                return quoteRepository.findQuotes(PageRequest.of(0, count, Sort.by("score").ascending()));
            case WORSE:
                return quoteRepository.findQuotes(PageRequest.of(0, count, Sort.by("score").descending()));
            default:
                throw new IllegalArgumentException("Undefined rating type");
        }
    }

    @Override
    public QuoteProjection getRandom() {
        return quoteRepository.findRandom();
    }

    @Override
    @Transactional
    public Quote save(PrincipalDto principal, String quoteContent) {
        Quote newQuote = Quote.builder().content(quoteContent).build();
        userRepository.findById(principal.getId()).ifPresent(newQuote::setUser);
        quoteRepository.save(newQuote);
        return newQuote;
    }

    @Override
    @Transactional
    public Quote updateContent(long quoteId, String content, PrincipalDto principalDto) {
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(QuoteNotFoundException::new);
        if (!Objects.equals(quote.getUser().getId(), principalDto.getId())) {
            throw new UnauthorizedActionException(principalDto.getLogin());
        }
        quote.setContent(content);
        return quote;
    }

    @Override
    @Transactional
    public void delete(long id, PrincipalDto principalDto) {
        Quote quote = quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
        if (!Objects.equals(quote.getUser().getId(), principalDto.getId())) {
            throw new UnauthorizedActionException(principalDto.getLogin());
        }
        quoteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void upvote(PrincipalDto principalDto, long quoteId) {
        voteRepository.findById(new VoteId(principalDto.getId(), quoteId)).ifPresentOrElse(
                v -> {
                    if (v.getVoteStatus().equals(VoteStatus.UPVOTE)) {
                        throw new UserAlreadyUpvoteException();
                    } else {
                        v.setVoteStatus(VoteStatus.UPVOTE);
                    }
                },
                () -> voteRepository.save(Vote.builder()
                        .userId(principalDto.getId()).voteStatus(VoteStatus.UPVOTE).quoteId(quoteId)
                        .build())
        );
    }

    @Override
    @Transactional
    public void downvote(PrincipalDto principalDto, long quoteId) {
        voteRepository.findById(new VoteId(principalDto.getId(), quoteId)).ifPresentOrElse(
                vote -> {
                    if (vote.getVoteStatus().equals(VoteStatus.DOWNVOTE)) {
                        throw new UserAlreadyDownvoteException();
                    } else {
                        vote.setVoteStatus(VoteStatus.DOWNVOTE);
                    }
                },
                () -> voteRepository.save(Vote.builder()
                        .userId(principalDto.getId()).voteStatus(VoteStatus.DOWNVOTE).quoteId(quoteId)
                        .build())
        );
    }
}
