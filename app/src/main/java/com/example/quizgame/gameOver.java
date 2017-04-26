package com.example.quizgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.quizgame.SettingsActivity.play;

public class gameOver extends AppCompatActivity {

    static MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        mp = MediaPlayer.create(this, R.raw.buttonclick);
        //set font for title
        TextView tx = (TextView)findViewById(R.id.gameOverText);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
    }

    public void onPlayAgainClick(View view){
        if(play) {
            mp.start();
        }
        Intent playagainIntent = new Intent(this, Questions.class);
        startActivity(playagainIntent);
    }
    public void onGoHomeClick(View view){
        if(play) {
            mp.start();
        }
        Intent gohomeIntent = new Intent(this, MainActivity.class);
        startActivity(gohomeIntent);
    }
    @Override
    public void onBackPressed(){

    }
}
