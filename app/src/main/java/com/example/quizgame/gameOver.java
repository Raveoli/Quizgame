package com.example.quizgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class gameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        //set font for title
        TextView tx = (TextView)findViewById(R.id.gameOverText);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
    }

    public void onPlayAgainClick(View view){
        Intent playagainIntent = new Intent(this, Questions.class);
        startActivity(playagainIntent);
    }
    public void onGoHomeClick(View view){
        Intent gohomeIntent = new Intent(this, MainActivity.class);
        startActivity(gohomeIntent);
    }
    @Override
    public void onBackPressed(){

    }
}
