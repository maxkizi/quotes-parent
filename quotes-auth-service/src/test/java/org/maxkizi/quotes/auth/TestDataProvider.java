package org.maxkizi.quotes.auth;

import org.maxkizi.quotes.auth.dto.SignupRequest;

public class TestDataProvider {
    public static SignupRequest buildSignupRequest(){
        return new SignupRequest("testlogin", "testpassword", "testName");
    }
}
