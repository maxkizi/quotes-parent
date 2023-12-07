package org.maxkizi.quotes.core.config;

import org.mapstruct.factory.Mappers;
import org.maxkizi.quotes.core.mapper.QuoteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public QuoteMapper quoteMapper() {
        return Mappers.getMapper(QuoteMapper.class);
    }
}
