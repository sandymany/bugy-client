package com.leticija.bugy;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

public class InterfaceFeatures {

    public static void changeTextViewVisibility (TextView textView, boolean visibility, String text, int color) {
        textView.setText(text);
        if (visibility) {
            textView.setVisibility(View.VISIBLE);
        }
        else {
            textView.setVisibility(View.INVISIBLE);
        }
        textView.setTextColor(ContextCompat.getColor(textView.getContext(),color));
    }
}
