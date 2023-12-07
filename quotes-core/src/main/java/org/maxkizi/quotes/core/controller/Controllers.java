package org.maxkizi.quotes.core.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Controllers {
    private static final String BASE_URI = "/api/v1";
    private static final String BY_ID = "/{id}";

    public static final String QUOTES = BASE_URI + "/quotes";
    public static final String QUOTE_BY_ID = BASE_URI + "/quote" + BY_ID;
    public static final String QUOTE_RANDOM = BASE_URI + "/quote/random";
    public static final String QUOTE_UPVOTE = BASE_URI + "/quote" + BY_ID + "/upvote";
    public static final String QUOTE_DOWNVOTE = BASE_URI + "/quote" + BY_ID + "/downvote";

}
