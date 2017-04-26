/*
* Display useful text to help player to play the game
* Written by Raveena R Hegde 04/21/2017
* */
package com.example.quizgame;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //set font for title
        TextView tx = (TextView)findViewById(R.id.helpTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
    }
}
