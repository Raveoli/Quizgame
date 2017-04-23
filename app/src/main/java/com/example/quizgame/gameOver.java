package com.example.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class gameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void onPlayAgainClick(View view){
        Intent playagainIntent = new Intent(this, Questions.class);
        startActivity(playagainIntent);
    }
    public void onGoHomeClick(View view){
        Intent gohomeIntent = new Intent(this, MainActivity.class);
        startActivity(gohomeIntent);
    }
}
