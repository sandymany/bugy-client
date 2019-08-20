package com.leticija.bugy.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leticija.bugy.InterfaceFeatures;
import com.leticija.bugy.R;
import com.leticija.bugy.log.Enter;
import com.leticija.bugy.log.LogInActivity;

public class HomeActivity extends AppCompatActivity {

    String TAG = "message";


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

        final Context context = getApplicationContext();

        // BUTTONI
        Button addBugsButton = findViewById(R.id.addBugs_button);
        Button viewCollectionButton = findViewById(R.id.viewCollection_button);
        Button settingsButton = findViewById(R.id.settings_button);

        //postaviti welcome text i user slikicu na toolbaru da je visible
        TextView homeText = findViewById(R.id.text_welcome);
        InterfaceFeatures.changeTextViewVisibility(homeText,true,username,R.color.dark_green_text);

        System.out.println("PASSED SESSION COOKIE: "+sessionCookie);

        // AUTOMATICALLY GET USER PROPERTIES
        UserProperties properties = new UserProperties(sessionCookie,context); //zahtjeva da mu server da stanje racuna od korisnika s određenim sessionCookijem
        System.out.println("GOT PROPERTIES: "+properties.getProperties());

        // 1. PROVJERITI JEL RESPONSE NIJE FALSE > ZNACI DA JE SESSION COOKIE VALID
        if(!(ResponseCheck.isResponseValid(properties.properties))) {
            Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
            startActivity(intent); //LogInActivity
        }

        // BUTTON FUNCTIONALITIES
        addBugsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ADDBUGS BUTTON CLICKED !");
                Intent addBugs = new Intent(context,AddBugsActivity.class);
                startActivity(addBugs);
            }
        });

        viewCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("VIEW COLLECTION BUTTON CLICKED !");
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SETTINGS BUTTON CLICKED !");
            }
        });



        //Intent intent = new Intent(HomeActivity.this, AddBugsActivity.class);
        //startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        return;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }
}