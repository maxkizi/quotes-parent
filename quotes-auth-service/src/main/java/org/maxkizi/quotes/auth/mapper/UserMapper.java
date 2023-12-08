package org.maxkizi.quotes.auth.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.maxkizi.quotes.auth.dto.SignupRequest;
import org.maxkizi.quotes.auth.model.User;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {
    User toEntity(SignupRequest dto);
}
