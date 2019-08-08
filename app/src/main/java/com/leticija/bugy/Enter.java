package com.leticija.bugy;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;
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
    public String logIn (TextView textView) throws IOException {
        sessionCookie = Session.sendCredentials(username,password);
        if(!(sessionCookie.equals("false".trim()))) {
            System.out.println("SESSION COOKIE: "+sessionCookie);
            System.out.println("LOGGING YOU IN");
            InterfaceFeatures.changeTextViewVisibility(textView,true,"Login successful!",R.color.success);
            return (sessionCookie);
        }
        System.out.println("#######WRONG########");
        InterfaceFeatures.changeTextViewVisibility(textView,true,"Invalid credentials.\nTry to register first.",R.color.warning);
        return ("false");
    }



}
