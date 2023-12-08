package org.maxkizi.quotes.auth.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Controllers {
    private static final String BASE_URI = "/api/v1";
    public static final String SIGNUP = BASE_URI + "/signup";
    public static final String AUTHENTICATE = BASE_URI + "/authenticate";
}
