package org.maxkizi.quotes.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.maxkizi.quotes.core.dto.projection.QuoteProjection;
import org.maxkizi.quotes.core.dto.response.QuoteDto;
import org.maxkizi.quotes.core.model.Quote;

@Mapper
public interface QuoteMapper {
    @Mappings({
            @Mapping(target = "postedBy", source = "quote.user.login")
    })
    QuoteDto toDto(Quote quote);

    @Mappings({
            @Mapping(target = "postedBy", source = "quoteProjection.login")
    })
    QuoteDto toDto(QuoteProjection quoteProjection);
}
