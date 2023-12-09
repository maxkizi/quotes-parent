package org.maxkizi.quotes.core.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VoteStatus {
    UPVOTE(1), DOWNVOTE(-1);
    private final int getIntValue;

}
