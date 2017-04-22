package com.example.quizgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Questions extends AppCompatActivity {
    private ArrayList<QuestionModel> questionList;
    private String[] questions,answers,imagePath;
    private Bitmap[] image;
    int score,nextQuestion=1;
    boolean lastQuestion=false;

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
        questions=new String[questionList.size()];
        answers=new String[questionList.size()];
        imagePath=new String[questionList.size()];
        image=new Bitmap[questionList.size()];
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

    public void onTrueBtnClick(View view){
        TextView question=(TextView)findViewById(R.id.question);
        TextView scoreInc=(TextView)findViewById(R.id.score);
        ImageView questionImage=(ImageView) findViewById(R.id.questionImage);

        if(answers[nextQuestion-1].equalsIgnoreCase("true") && lastQuestion==false){
            score+=3;
        }
        scoreInc.setText(Integer.toString(score));
        if(nextQuestion<questionList.size()){
            question.setText(questions[nextQuestion]);
            questionImage.setImageBitmap(image[nextQuestion]);
            nextQuestion++;
        }else{
            lastQuestion=true;
            checkIfHighScore();
        }
        //Log.d("d",Integer.toString(score));
    }

    public void onFalseBtnClick(View view){
        TextView question=(TextView)findViewById(R.id.question);
        TextView scoreInc=(TextView)findViewById(R.id.score);
        ImageView questionImage=(ImageView) findViewById(R.id.questionImage);

        if(answers[nextQuestion-1].equalsIgnoreCase("False") && lastQuestion==false){
            score+=3;
        }
        scoreInc.setText(Integer.toString(score));
        //Log.d("d",Integer.toString(score));
        if(nextQuestion<questionList.size()){
            question.setText(questions[nextQuestion]);
            questionImage.setImageBitmap(image[nextQuestion]);
            nextQuestion++;
        }
        else{
            lastQuestion=true;
            checkIfHighScore();
        }
        //Log.d("d",Integer.toString(score));
    }

    public void checkIfHighScore()
    {
        TextView scoreInc=(TextView)findViewById(R.id.score);
        int currentScore = Integer.parseInt(scoreInc.getText().toString());
        boolean isHigh = HighscoresManager.checkIfHighScore(currentScore,this.getApplicationContext());
        if(isHigh)
        {
            Intent hIntent = new Intent(this, highScoreName.class);
            hIntent.putExtra(QuizGameConstants.highScore,currentScore);
            startActivity(hIntent);
        } else {
            Toast.makeText(this.getApplicationContext()," GAME OVER!", Toast.LENGTH_LONG).show();
            Intent highScoreIntent = new Intent(this, MainActivity.class);
            startActivity(highScoreIntent);
        }
    }
}
