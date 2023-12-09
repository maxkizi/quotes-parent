package org.maxkizi.quotes.core.converter;

import org.maxkizi.quotes.core.enumeration.VoteStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class VoteStatusConverter implements AttributeConverter<VoteStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(VoteStatus voteStatus) {
        return voteStatus.getGetIntValue();
    }

    @Override
    public VoteStatus convertToEntityAttribute(Integer intValue) {
        if (intValue == 1) {
            return VoteStatus.UPVOTE;
        } else if (intValue == -1) {
            return VoteStatus.DOWNVOTE;
        } else {
            throw new IllegalArgumentException("Not a valid like value from db: " + intValue);
        }
    }
}
