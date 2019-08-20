package com.leticija.bugy.home;

public class ResponseCheck {

    public boolean isResponseValid (String response) {
        if (!(response.trim().equals("false"))) {
            return true;
        }
        return false;
    }
}
