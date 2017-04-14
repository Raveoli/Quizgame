package com.example.quizgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

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
        Intent addQuestionIntent = new Intent(this, AddQuestionActivity.class);
        startActivity(addQuestionIntent);
    }

    public void onPlayBtnClick(View view)
    {

    }

    public void onHighScoreBtnClick(View view)
    {
        Intent hsIntent = new Intent(this, HighscoresActivity.class);
        startActivity(hsIntent);
    }
    public void onHelpBtnClick(View view)
    {
        Intent hIntent = new Intent(this, HelpActivity.class);
        startActivity(hIntent);
    }
    public void onSettingsBtnClick(View view)
    {
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
