package com.leticija.bugy.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.leticija.bugy.InterfaceFeatures;
import com.leticija.bugy.R;
import com.leticija.bugy.log.Enter;

public class HomeActivity extends AppCompatActivity {

    String sessionCookie = Enter.sessionCookie;
    String password = Enter.password;
    String username = Enter.username;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_home);

        Context context = getApplicationContext();

        //postaviti welcome text na toolbaru da je visible
        TextView homeText = findViewById(R.id.text_welcome);
        InterfaceFeatures.changeTextViewVisibility(homeText,true,username,R.color.black);

        System.out.println("PASSED SESSION COOKIE: "+sessionCookie);
        UserProperties properties = new UserProperties(sessionCookie,context); //zahtjeva da mu server da stanje racuna od korisnika s određenim sessionCookijem
        System.out.println("GOT PROPERTIES: "+properties.getProperties());
        //Intent intent = new Intent(HomeActivity.this, AddBugsActivity.class);
        //startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        return;
    }
}