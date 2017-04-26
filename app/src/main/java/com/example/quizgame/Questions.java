package com.example.quizgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static com.example.quizgame.SettingsActivity.play;

public class Questions extends AppCompatActivity implements SensorEventListener {
    static MediaPlayer mp;
    private ArrayList<QuestionModel> questionList;
    private String[] questions,answers,imagePath;
    private Bitmap[] image;
    int score,nextQuestion=1;
    boolean lastQuestion=false,change=false;
    TextView question,scoreInc;
    ImageView questionImage;


    private float mAccelNoGrav;
    private float mAccelWithGrav;
    private float mLastAccelWithGrav;

    ArrayList<Float> z = new ArrayList<Float>();

    public static float finalZ;

    public static boolean shakeIsHappening;
    public static int beatnumber = 0;
    public static float highZ;
    public static float lowZ;
    public static boolean flick;
    public static boolean pull;
    private static SensorManager manager;
    private static Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        mp = MediaPlayer.create(this, R.raw.buttonclick);

        question=(TextView)findViewById(R.id.question);
        scoreInc=(TextView)findViewById(R.id.score);
        questionImage=(ImageView) findViewById(R.id.questionImage);
        change=false;
        displayQuestions(this.getApplicationContext());

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_FASTEST);

        if (!manager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST)) {
            Log.d("Problem","Problem with Accelerometer - Shaking will not work");
        }
        ;


        mAccelNoGrav = 0.00f;
        mAccelWithGrav = SensorManager.GRAVITY_EARTH;
        mLastAccelWithGrav = SensorManager.GRAVITY_EARTH;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(!change) {
            float x = event.values[0];
            float y = event.values[1];
            z.add((event.values[2]) - SensorManager.GRAVITY_EARTH);
            mLastAccelWithGrav = mAccelWithGrav;
            mAccelWithGrav = (float) Math.sqrt(x * x + y * y + z.indexOf(z.size() - 1) * z.indexOf(z.size() - 1));
            float delta = mAccelWithGrav - mLastAccelWithGrav;
            mAccelNoGrav = mAccelNoGrav * 0.9f + delta; // Low-cut filter

            //Log.d("CValues","z" + event.values[2]);
            //Log.d("CValues","mAccelNoGrav" + mAccelNoGrav);
            //Log.d("CValues","mAccelWithGrav" + mAccelWithGrav);
            //Log.d("CValues","delta" + delta);

            if (mAccelNoGrav > 8.5) {
                shakeIsHappening = true;

                z.clear();

                if (z.indexOf(z.size() - 2) > z.indexOf(z.size() - 1)) {
                    Log.d("zIndex", " Z shrinking" + z);
                } else if (z.indexOf(z.size() - 2) < z.indexOf(z.size() - 1)) {
                    Log.d("zIndex", " Z growing" + z);
                }

            }
            //Log.d("CValues","shakeIsHappening" + shakeIsHappening);

            if (shakeIsHappening == true && mAccelNoGrav < 2) {

                finalZ = z.get(z.size() - 1);
                highZ = z.get(z.size() - 1);
                lowZ = z.get(z.size() - 1);
                //Log.d("Values","finalZ" + finalZ);
                for (int i = 0; i < z.size(); i++) {
                    // Log.d("Values","z.get(i)" + z.get(i));
                    if (z.get(i) > highZ) {
                        highZ = z.get(i);
                    } else if ((z.get(i) < lowZ)) {
                        lowZ = z.get(i);
                    }
                    if (highZ == finalZ) {
                        flick = true;
                        pull = false;
                    } else if (lowZ == finalZ) {
                        flick = false;
                        pull = true;

                    }

                    if (flick == true && mAccelNoGrav > -1.9) {
                        //beatnumber++;
                        //Log.d("Clicked", "Flip Backwards click number: " + beatnumber + "\n" + "PA: "+ mLastAccelWithGrav + " CA:" + mAccelNoGrav + "\n " + "Lz " + z.indexOf(z.size() - 2) + "z " + z.indexOf(z.size() - 1) + "\n" + "\n");
                    /*try {
                        Thread.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                        trueOptionClicked();
                        Toast.makeText(this.getApplicationContext(), " True clicked!", Toast.LENGTH_LONG).show();
                        shakeIsHappening = false;
                    }

                    if (flick == true && mAccelNoGrav < -1.9) {
                        //beatnumber++;
                        //Log.d("Clicked", "Flip Forwards click number: " + beatnumber + "\n" + "PA: "+ mLastAccelWithGrav + " CA:" + mAccelNoGrav + "\n " + "Lz " + z.indexOf(z.size() - 2) + "z " + z.indexOf(z.size() - 1) + "\n" + "\n");
                    /*try {
                        Thread.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                        falseOptionClicked();
                        Toast.makeText(this.getApplicationContext(), " False clicked!", Toast.LENGTH_LONG).show();
                        shakeIsHappening = false;
                    }

                    z.clear();

                }
            }
        }
        else{
            manager.unregisterListener(this);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onBackPressed(){

    }

    public void displayQuestions(Context context){
        questionList=QuestionsManager.getQuestionsList();

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
        //Log.d("Clicked","Enter display questions");
        question.setText(questions[0]);
        questionImage.setImageBitmap(image[0]);
    }

    public void onTrueBtnClick(View view){
        if(play) {
            mp.start();
        }
        trueOptionClicked();
    }

    public void onFalseBtnClick(View view){
        if(play) {
            mp.start();
        }
        falseOptionClicked();
    }

    public void onSettingsClick(View view){
        Intent SettingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(SettingsIntent);
    }

    public void onHelpClick(View view){
        Intent HelpIntent = new Intent(this, HelpActivity.class);
        startActivity(HelpIntent);
    }

    public void trueOptionClicked(){
        //Log.d("Clicked","nextQuestion"+nextQuestion);
        //Log.d("Clicked","lastQuestion"+lastQuestion);
        if(answers[nextQuestion-1].equalsIgnoreCase("true") && lastQuestion==false){
            score+=3;
        }
        //Log.d("Clicked","score"+score);
        scoreInc.setText(Integer.toString(score));
        if(nextQuestion<questionList.size()){
            question.setText(questions[nextQuestion]);
            questionImage.setImageBitmap(image[nextQuestion]);
            //Log.d("Clicked","Question"+questions[nextQuestion]);
            nextQuestion++;
            //Log.d("Clicked","incnextQuestion"+nextQuestion);
        }else{
            lastQuestion=true;
            nextQuestion=1;
            checkIfHighScore();
        }
        //Log.d("Clicked","---------------------------");
        //Log.d("d",Integer.toString(score));
    }

    public void falseOptionClicked(){

        //Log.d("Clicked","nextQuestion"+nextQuestion);
        //Log.d("Clicked","lastQuestion"+lastQuestion);


        if(answers[nextQuestion-1].equalsIgnoreCase("False") && lastQuestion==false){
            score+=3;
        }
        //Log.d("Clicked","score"+score);
        scoreInc.setText(Integer.toString(score));
        //Log.d("d",Integer.toString(score));
        if(nextQuestion<questionList.size()){
            question.setText(questions[nextQuestion]);
            questionImage.setImageBitmap(image[nextQuestion]);
            //Log.d("Clicked","Question"+questions[nextQuestion]);
            nextQuestion++;
            //Log.d("Clicked","incnextQuestion"+nextQuestion);
        }
        else{
            lastQuestion=true;
            nextQuestion=1;
            checkIfHighScore();
        }
        //Log.d("Clicked","---------------------------");
        //Log.d("d",Integer.toString(score));
    }
    public void checkIfHighScore()
    {
        TextView scoreInc=(TextView)findViewById(R.id.score);
        int currentScore = Integer.parseInt(scoreInc.getText().toString());
        boolean isHigh = HighscoresManager.checkIfHighScore(currentScore,this.getApplicationContext());
        if(isHigh)
        {
            lastQuestion=false;
            change=true;
            score=0;
            Intent hIntent = new Intent(this, highScoreName.class);
            hIntent.putExtra(QuizGameConstants.highScore,currentScore);
            startActivity(hIntent);
        } else {
            //Toast.makeText(this.getApplicationContext()," GAME OVER!", Toast.LENGTH_LONG).show();
            lastQuestion=false;
            change=true;
            score=0;
            Intent highScoreIntent = new Intent(this, gameOver.class);
            startActivity(highScoreIntent);
        }
    }
}
