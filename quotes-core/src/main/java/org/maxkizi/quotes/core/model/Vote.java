package org.maxkizi.quotes.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.maxkizi.quotes.core.converter.VoteStatusConverter;
import org.maxkizi.quotes.core.enumeration.VoteStatus;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(VoteId.class)
@Table(name = "votes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Vote {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "quote_id")
    private Long quoteId;
    @Convert(converter = VoteStatusConverter.class)
    @Column(name = "int_value")
    private VoteStatus voteStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", updatable = false, insertable = false)
    private Quote quote;
}

