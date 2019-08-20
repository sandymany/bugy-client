package com.leticija.bugy.home;

public class ResponseCheck{

    public static boolean isResponseValid (String response) {
        return !response.trim().equals("false");
    }
}
