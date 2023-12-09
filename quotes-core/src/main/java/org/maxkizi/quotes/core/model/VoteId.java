package org.maxkizi.quotes.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VoteId implements Serializable {
    private Long userId;
    private Long quoteId;
}
