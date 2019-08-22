package com.leticija.bugy.home;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.leticija.bugy.R;
import com.leticija.bugy.log.Enter;

import java.util.HashMap;

public class AddBySearch extends AppCompatActivity {

    String sessionCookie = Enter.sessionCookie;
    HashMap<String,String> request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_addbugs_search);

        final Context context = getApplicationContext();


        //FIND EVERYTHING YOU NEED
        final EditText searchInput = findViewById(R.id.searchBugs_editText);
        Button searchButton = findViewById(R.id.search_button);
        ScrollView scrollView = findViewById(R.id.search_scrollView);
        LinearLayout linearLayout = findViewById(R.id.search_linearLayout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT);


        /*
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < 10; i++) {
            CardView cardView = (CardView) inflater.inflate(R.layout.card_view_template,null);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.bugy_logo);
            layoutParams.leftMargin = 10;
            layoutParams.gravity = Gravity.RIGHT;
            imageView.setLayoutParams(layoutParams);
            cardView.addView(imageView);

            View viewDivider = new View(this);
            viewDivider.setBackgroundColor(getResources().getColor(R.color.gray));
            LinearLayout.LayoutParams dividerparams = new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT);
            dividerparams.leftMargin = 250;
            dividerparams.topMargin = 20;
            dividerparams.bottomMargin = 20;
            viewDivider.setLayoutParams(dividerparams);
            cardView.addView(viewDivider);

            TextView textView = new TextView(this);
            textView.setText("SOMETHING ABOUT INSECT...");
            LinearLayout.LayoutParams textparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textparams.leftMargin = 270;
            textparams.topMargin = 100;
            textView.setLayoutParams(textparams);
            cardView.addView(textView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("CARD CLICKED !");
                }
            });

            linearLayout.addView(cardView);

        }
        */


        //linearLayout.addView(cardView);

        // MAKE WIDGET FOR SCROLLVIEW
        /*
        for(int i = 0; i < 5; i++) {
            CardView cardView = findViewById(R.id.template_cardView);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.bugy_logo);
            //cardView.addView(imageView);
            linearLayout.addView(cardView);
        }
        */

        // BUTTON FUNCTIONALITIES

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SEARCHING: "+searchInput.getText().toString());
                request = new HashMap<>();
                request.put("sessionCookie",sessionCookie);
                request.put("toSearch",searchInput.getText().toString());
                System.out.println("YOUR HASHMAP: "+ServerLink.mapToString(request));
                ServerLink insectSearch = new ServerLink(sessionCookie,"/home/searchBugs");
                String searchaniKukci = insectSearch.getServerResponse(searchInput.getText().toString(),context);
                System.out.println("RESPONSE: "+searchaniKukci);

            }
        });

    }
}