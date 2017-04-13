package com.example.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //load questions
        QuestionsManager.readAllQuestionsFromFile(this.getApplicationContext());
    }


    public void onAddQuestionBtnClick(View view)
    {
        Intent addQuestionIntent = new Intent(this, AddQuestionActivity.class);
        startActivity(addQuestionIntent);
    }
}
