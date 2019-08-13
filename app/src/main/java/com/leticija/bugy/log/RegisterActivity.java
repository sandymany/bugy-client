package com.leticija.bugy.log;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leticija.bugy.R;
import com.leticija.bugy.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        final Context context = getApplicationContext();


        Button registerButton = findViewById(R.id.button3);
        final EditText usernameText = findViewById(R.id.editText3);
        final EditText passwordText = findViewById(R.id.editText4);
        final TextView textView = findViewById(R.id.textView8);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                Enter register = new Enter(username,password,context);
                String serverResponse = register.register(textView);
                System.out.println("SERVER RESPONSE AT REGISTER: "+serverResponse);
                if(serverResponse != null) {
                    System.out.println("GOING TO ANOTHER ACTIVITY!!!!!");
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    //intent.putExtra("sessionCookie",serverResponse);
                    startActivity(intent);
                }
            }
        });
    }
}
