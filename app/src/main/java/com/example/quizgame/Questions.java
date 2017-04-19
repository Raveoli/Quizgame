package com.example.quizgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Questions extends AppCompatActivity {
    private ArrayList<QuestionModel> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        displayQuestions(this.getApplicationContext());
    }

    public void displayQuestions(Context context){
        questionList=QuestionsManager.getQuestionsList();
        TextView question=(TextView)findViewById(R.id.question);
        ImageView questionImage=(ImageView) findViewById(R.id.questionImage);
        String[] questions=new String[questionList.size()];
        String[] answers=new String[questionList.size()];
        String[] imagePath=new String[questionList.size()];
        Bitmap[] image=new Bitmap[questionList.size()];
        //int[] score;
        Collections.shuffle(questionList); //Randomly display questions
        int i=0;
        for (QuestionModel q:questionList){
            questions[i]=q.getQuestion();
            answers[i]=q.getAnswer();
            imagePath[i]=q.getImagePath();
            File mydir = context.getDir(QuizGameConstants.imageDirectoryPath, Context.MODE_PRIVATE);
            File fileWithinMyDir = new File(mydir, imagePath[i]);
            Bitmap bitmap = BitmapFactory.decodeFile(fileWithinMyDir.getAbsolutePath());
            image[i]=bitmap;
            //Log.d("Bitmap",image[i].toString());
            i++;
        }
        question.setText(questions[0]);
        questionImage.setImageBitmap(image[0]);
    }
}
