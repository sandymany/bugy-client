package com.leticija.bugy.log;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.leticija.bugy.R;

public class Enter {

    String sessionCookie;
    String username;
    String password;

    Enter (String username, String password) {
        this.username = username;
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String logIn (TextView textView) throws NullPointerException{

        sessionCookie = Session.sendCredentials(username,password,"login");
        try {
            if (!(sessionCookie.equals("false".trim()))) {
                System.out.println("SESSION COOKIE: " + sessionCookie);
                System.out.println("LOGGING YOU IN");
                InterfaceFeatures.changeTextViewVisibility(textView, true, "Login successful!", R.color.success);
                return (sessionCookie);
            }
            System.out.println("####### WRONG ########");
            InterfaceFeatures.changeTextViewVisibility(textView, true, "Invalid credentials.\nTry to register first.", R.color.warning);
            //return ("false");
            return (null);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            InterfaceFeatures.changeTextViewVisibility(textView,true,"Sorry\nConnection is down",R.color.warning);
        }
        return (null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String register (TextView textView) throws NullPointerException {
        sessionCookie = Session.sendCredentials(username,password,"register");
        try {
            if (sessionCookie.equals("false".trim())) {
                InterfaceFeatures.changeTextViewVisibility(textView, true, "Credentials should\ncontain max 15 characters", R.color.warning);
                //return (sessionCookie);
                return (null);
            }
            else if (sessionCookie.equals("true".trim())) {
                InterfaceFeatures.changeTextViewVisibility(textView,true,"User already exists",R.color.warning);
                //return (sessionCookie);
                return (null);
            }
            System.out.println("REGISTRATION SUCCESSFUL! : " + sessionCookie);
            InterfaceFeatures.changeTextViewVisibility(textView, true, "Login successful!", R.color.success);
            return (sessionCookie);

        } catch (NullPointerException ex) {
            InterfaceFeatures.changeTextViewVisibility(textView,true,"Sorry\nConnection is down",R.color.warning);
            ex.printStackTrace();
        }
        return (null);
    }



}
