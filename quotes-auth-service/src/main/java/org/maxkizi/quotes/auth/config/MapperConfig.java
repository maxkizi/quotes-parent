package org.maxkizi.quotes.auth.config;

import org.mapstruct.factory.Mappers;
import org.maxkizi.quotes.auth.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
}
