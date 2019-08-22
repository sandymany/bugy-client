package com.leticija.bugy.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leticija.bugy.R;
import com.leticija.bugy.log.Enter;

public class AddBugsActivity extends AppCompatActivity {

    String TAG = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_addbugs);

        // BUTTONS
        Button customAddButton = findViewById(R.id.customAdd_button);
        Button addBySearchButton = findViewById(R.id.addBySearch_button);

        // BUTTON FUNCTIONALITIES
        addBySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBugsActivity.this,AddBySearch.class);
                startActivity(intent);
            }
        });
        customAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CUSTOMADD BUTTON CLICKED !");
            }
        });


    }
}
