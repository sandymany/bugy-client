package com.leticija.bugy;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

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
        String toReturn;
        try {
            if (!(sessionCookie.equals("false".trim()))) {
                System.out.println("SESSION COOKIE: " + sessionCookie);
                System.out.println("LOGGING YOU IN");
                InterfaceFeatures.changeTextViewVisibility(textView, true, "Login successful!", R.color.success);
                return (sessionCookie);
                //toReturn = sessionCookie;
            }
            System.out.println("####### WRONG ########");
            InterfaceFeatures.changeTextViewVisibility(textView, true, "Invalid credentials.\nTry to register first.", R.color.warning);
            return ("false");
            //toReturn = "false";
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

            if (!(sessionCookie.equals("true".trim()))) {
                System.out.println("REGISTRATION SUCCESSFUL! : " + sessionCookie);
                InterfaceFeatures.changeTextViewVisibility(textView, true, "Successfully registered!", R.color.success);
                return (sessionCookie);
            }
            System.out.println("INVALID REGISTRATION");
            InterfaceFeatures.changeTextViewVisibility(textView, true, "User already exists!", R.color.warning);
            return (sessionCookie);
        } catch (NullPointerException ex) {
            InterfaceFeatures.changeTextViewVisibility(textView,true,"Sorry\nConnection is down",R.color.warning);
            ex.printStackTrace();
        }
        return (null);
    }



}
