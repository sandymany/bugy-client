package com.leticija.bugy;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

public class Enter {

    String sessionCookie;
    String username;
    String password;

    Enter (String username, String password){
        this.username = username;
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String logIn () throws IOException {
        sessionCookie = Session.sendCredentials(username,password);
        if(!(sessionCookie.equals("false"))) {
            return ("successfully logged in !");
        }
        return ("Sorry, gotta register first!");
    }



}
