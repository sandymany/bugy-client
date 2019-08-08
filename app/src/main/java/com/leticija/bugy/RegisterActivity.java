package com.leticija.bugy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Button registerButton = findViewById(R.id.button3);
        final EditText usernameText = findViewById(R.id.editText3);
        final EditText passwordText = findViewById(R.id.editText4);
        final TextView textView = findViewById(R.id.textView8);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                Enter register = new Enter(username,password);
                String serverResponse = register.register(textView);

            }
        });

    }
}
