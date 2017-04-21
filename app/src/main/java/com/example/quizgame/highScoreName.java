package com.example.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class highScoreName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_name);
    }

    public void onNextBtnClick(View view){
        Intent highScoreIntent = new Intent(this, HighscoresActivity.class);
        startActivity(highScoreIntent);
    }
}
