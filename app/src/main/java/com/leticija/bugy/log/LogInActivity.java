package com.leticija.bugy.log;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leticija.bugy.R;
import com.leticija.bugy.home.HomeActivity;

public class LogInActivity extends AppCompatActivity {

    String TAG = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        final Context context = getApplicationContext();

        //ne smije se videti ikona usera i "welcome"
        Group welcomeGroup = findViewById(R.id.groupWelcome);
        welcomeGroup.setVisibility(View.INVISIBLE);

        final TextView textView = findViewById(R.id.textView4);
        Button submitButton = findViewById(R.id.button);
        Button registerButton = findViewById(R.id.button2);
        final EditText usernameText = findViewById(R.id.username_input);
        final EditText passwordText = findViewById(R.id.password_input);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                Enter login = new Enter(username,password,context);
                System.out.println("sending credentials to server: "+username+" "+password);
                String serverResponse = login.logIn(textView); //ako je login uspješan, server vrati session cookie. Kasnije bum morala parsati userovo stanje racuna
                if(serverResponse != null) {
                    System.out.println("GOING TO ANOTHER ACTIVITY FROM LoginActivity "+serverResponse);
                    Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

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
