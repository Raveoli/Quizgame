package com.example.quizgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class highScoreName extends AppCompatActivity {
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_name);
        TextView tx = (TextView)findViewById(R.id.highScoreText);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
        score = (int) getIntent().getSerializableExtra(QuizGameConstants.highScore);
    }

    public void onNextBtnClick(View view)
    {
        // add validation for name
        EditText name = (EditText) findViewById(R.id.highscoreNameText);
        String namet = name.getText().toString();

        if(namet.isEmpty())
        {
            Toast.makeText(this.getApplicationContext(),"Please fill in the name", Toast.LENGTH_SHORT).show();
            return;
        }
        //add the highscore name
        HighscoreModel h1 = new HighscoreModel(namet,score,new Date());
        HighscoresManager.addHighscoreToFile(h1,this.getApplicationContext());

        Intent highScoreIntent = new Intent(this, HighscoresActivity.class);
        startActivity(highScoreIntent);
    }
}
