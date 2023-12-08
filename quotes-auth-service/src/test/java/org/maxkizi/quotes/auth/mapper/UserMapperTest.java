package org.maxkizi.quotes.auth.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.maxkizi.quotes.auth.TestDataProvider;
import org.maxkizi.quotes.auth.dto.SignupRequest;
import org.maxkizi.quotes.auth.model.User;

class UserMapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toEntityTest(){
        SignupRequest signupRequest = TestDataProvider.buildSignupRequest();
        User user = userMapper.toEntity(signupRequest);
        Assertions.assertTrue(user.isAccountNonExpired());
        Assertions.assertTrue(user.isEnabled());
        Assertions.assertTrue(user.isCredentialsNonExpired());
        Assertions.assertTrue(user.isAccountNonLocked());

        Assertions.assertEquals(user.getName(), signupRequest.getName());
        Assertions.assertEquals(user.getLogin(), signupRequest.getLogin());
        Assertions.assertEquals(user.getPassword(), signupRequest.getPassword());


    }

}