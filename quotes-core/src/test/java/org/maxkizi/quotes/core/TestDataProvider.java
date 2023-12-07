package org.maxkizi.quotes.core;

import lombok.experimental.UtilityClass;
import org.maxkizi.quotes.core.model.Quote;
import org.maxkizi.quotes.core.model.User;

@UtilityClass
public class TestDataProvider {
    public User buildUser(int idx){
        return User.builder()
                .login("login" + idx)
                .build();
    }

    public Quote buildQuote(long idx){
        return Quote.builder()
                .content("content" + idx)
                .score(idx)
                .build();
    }
}
