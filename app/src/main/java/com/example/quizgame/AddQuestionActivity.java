/**
 * This file contains all the functionality of the Add question screen and allows the user to add
 * a new question to the game.
 * Written by Pooja.N.S 04/09/2017
 */
package com.example.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AddQuestionActivity extends AppCompatActivity {

    final private int SELECT_FILE = 1;

    private QuestionModel questionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_add_question);

        //set font for title
        TextView tx = (TextView)findViewById(R.id.questionTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);

        questionModel = new QuestionModel();
    }

    //handle add photo button click
    public  void onAddPhotoBtnClick(View view)
    {
        //check if app has permission to open gallery
        boolean isValid = Utils.checkPermission(AddQuestionActivity.this);

        //send intent to open gallery
        if(isValid)
        {
            openGalleryIntent();
        }
    }

    //issue an intent to open Gallery and choose an image
    private void openGalleryIntent()
    {
        Intent intent = new Intent();
        //displays only images
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    //override to get the user reply for check permission to open Gallery
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  //open the  gallery
                    openGalleryIntent();
                }
                break;
        }
    }

    //process the result from the Gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == SELECT_FILE)
            {
                //read the image from Gallery
                Bitmap bm=null;
                if (data != null) {
                    try
                    {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //set the image to the imageview
                ImageView previewImage = (ImageView) findViewById(R.id.imagePreview);
                previewImage.setImageBitmap(bm);
                questionModel.setClueImage(bm);
            }
        }
    }

    //handle add button click
    public void onAddBtnClick(View view)
    {
        //TODO - validate the inputs
        //create a quesion model object with the chosen answers and save it to file
        EditText quesText = (EditText) findViewById(R.id.questionText);
        questionModel.setQuestion(quesText.getText().toString());

        Log.d("AddQuestionActivity", "onAddBtnClick: writing to file...");
        //write to file
        QuestionsManager.addQuestionToFile(questionModel,getApplicationContext());

        QuestionsManager.readAllQuestionsFromFile(this.getApplicationContext());
        resetScreenToDefault();

        Toast.makeText(this,"The question has been added successfully",Toast.LENGTH_SHORT).show();
    }

    private void resetScreenToDefault()
    {
        EditText quesText = (EditText) findViewById(R.id.questionText);
        quesText.setText("");

        RadioButton trueBtn = (RadioButton) findViewById(R.id.radio_true);
        trueBtn.setChecked(false);

        RadioButton falseBtn = (RadioButton) findViewById(R.id.radio_false);
        falseBtn.setChecked(false);

        ImageView previewImage = (ImageView) findViewById(R.id.imagePreview);
        previewImage.setImageResource(android.R.color.transparent);


    }

    //handle radio button click
    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_true:
                if (checked)
                    questionModel.setAnswer(QuizGameConstants.trueOption);
                    break;
            case R.id.radio_false:
                if (checked)
                    questionModel.setAnswer(QuizGameConstants.falseOption);
                    break;
        }
    }



}
