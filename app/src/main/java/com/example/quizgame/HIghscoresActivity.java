package com.example.quizgame;

import android.graphics.Typeface;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

class HighscoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        HighscoresManager.addSampleHighscores(this.getApplicationContext());

        HighscoresManager.readAllHighscoresFromFile(this.getApplicationContext());

        loadHighscoresIntoUI();

        //set font for title
        TextView tx = (TextView)findViewById(R.id.highscoretitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
    }

    private void loadHighscoresIntoUI()
    {
        HighscoreModel[] scores = new HighscoreModel[5];
        scores  = HighscoresManager.getScoresList().toArray(scores);

        TextView name1 = (TextView) findViewById(R.id.firstname);
        TextView score1 = (TextView) findViewById(R.id.firstscore);
        name1.setText(scores[0].getName());
        score1.setText(scores[0].getHighScore().toString());

        TextView name2 = (TextView) findViewById(R.id.secondname);
        TextView score2 = (TextView) findViewById(R.id.secondscore);
        name2.setText(scores[1].getName());
        score2.setText(scores[1].getHighScore().toString());

        TextView name3 = (TextView) findViewById(R.id.thirdname);
        TextView score3 = (TextView) findViewById(R.id.thirdscore);
        name3.setText(scores[2].getName());
        score3.setText(scores[2].getHighScore().toString());

        TextView name4 = (TextView) findViewById(R.id.fourthname);
        TextView score4 = (TextView) findViewById(R.id.fourthscore);
        name4.setText(scores[3].getName());
        score4.setText(scores[3].getHighScore().toString());

        TextView name5 = (TextView) findViewById(R.id.fifthname);
        TextView score5 = (TextView) findViewById(R.id.fifthscore);
        name5.setText(scores[4].getName());
        score5.setText(scores[4].getHighScore().toString());

    }

    public void onOKBtnClick(View view)
    {
        finish();
    }
}
