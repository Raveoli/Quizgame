/*
* Project: Quiz game based on food trivia
* Developers: Pooja (pxn161830) and Raveena (rxh160530)
* Features:
* 1. True/False game play to answer questions displayed in random order. Points get added according to the correct answer.
* 2. Flipping the phone forwards and backwards to make the player answer questions in a different way
* 3. Toggling of sound in the app
* 4. Adding of additional questions by the player
* 5. Help screen to provide helpful text on gameplay for the player
* */
package com.example.quizgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.quizgame.SettingsActivity.play;

public class MainActivity extends AppCompatActivity {

    static MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.buttonclick);

        //set font for title
        TextView tx = (TextView)findViewById(R.id.logo);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);


        //set animations for all the buttons
        Button play = (Button) findViewById(R.id.playbtn);
        Button aq = (Button) findViewById(R.id.addquestionbtn);
        Button hs = (Button) findViewById(R.id.highscorebtn);
        Button h = (Button) findViewById(R.id.helpbtn);
        Button s = (Button) findViewById(R.id.settingsbtn);

        animateButton(play);
        animateButton(aq);
        animateButton(hs);
        animateButton(h);
        animateButton(s);

        //load questions
        QuestionsManager.readAllQuestionsFromFile(this.getApplicationContext());
    }


    public void onAddQuestionBtnClick(View view)
    {
        if(play) {
            mp.start();
        }
        Intent addQuestionIntent = new Intent(this, AddQuestionActivity.class);
        startActivity(addQuestionIntent);
    }

    public void onPlayBtnClick(View view)
    {
        if(play) {
            mp.start();
        }
        //check if there are any questions if so redirect
        boolean valid  = QuestionsManager.hasQuestions(getApplicationContext());
        if(valid)
        {
            Intent plIntent = new Intent(this, Questions.class);
            startActivity(plIntent);
        } else
            Toast.makeText(this.getApplicationContext(),"Please add questions.",Toast.LENGTH_SHORT).show();

    }

    public void onHighScoreBtnClick(View view)
    {
        if(play) {
            mp.start();
        }
        Intent hsIntent = new Intent(this, HighscoresActivity.class);
        startActivity(hsIntent);
    }
    public void onHelpBtnClick(View view)
    {
        if(play) {
            mp.start();
        }
        Intent hIntent = new Intent(this, HelpActivity.class);
        startActivity(hIntent);
    }
    public void onSettingsBtnClick(View view)
    {
        if(play) {
            mp.start();
        }
        Intent sIntent = new Intent(this, SettingsActivity.class);
        startActivity(sIntent);
    }


    public void animateButton(Button button)
    {
        Animation animation = new TranslateAnimation(-500,0,0, 0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                secondFunction();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        button.startAnimation(animation);
    }
}
