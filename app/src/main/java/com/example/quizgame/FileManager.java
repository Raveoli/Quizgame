package com.example.quizgame;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

import static java.lang.Integer.parseInt;

/**
 * Created by raveenahegde on 11/04/17.
 */


//Class that manages file operations
public class FileManager {

    public static ArrayList<QuestionModel> readFromQuestionsFile(String filePath,Context context)
    {
        ArrayList<QuestionModel> questionsList = new ArrayList<QuestionModel>();
        try {
            InputStream inputStream = context.openFileInput(filePath);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] splitString =  receiveString.split("\t");
                    QuestionModel c = new QuestionModel(splitString[0],splitString[1],splitString[2],parseInt(splitString[3]));
                    questionsList.add(c);
                }


                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("FileManager", "File not found: " + e.toString());

        } catch (IOException e) {
            Log.e("FileManager", "Cannot read file: " + e.toString());

        }
        return questionsList;
    }

    public static ArrayList<HighscoreModel> readFromHighscoreFile(String filePath,Context context)
    {
        ArrayList<HighscoreModel> highscoreList = new ArrayList<HighscoreModel>();
        try {
            InputStream inputStream = context.openFileInput(filePath);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] splitString =  receiveString.split("\t");
                    Date timeStamp=covertToDate(splitString[2]);
                    //add to the contact list to be displayed
                    HighscoreModel c = new HighscoreModel(splitString[0],parseInt(splitString[1]), timeStamp);
                    highscoreList.add(c);
                }


                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("FileManager", "File not found: " + e.toString());

        } catch (IOException e) {
            Log.e("FileManager", "Cannot read file: " + e.toString());

        }
        return highscoreList;
    }


    //writes a string data to a text file in internal storage at the path
    public static void writeToFile(String data,Context context,String filepath) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filepath, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("FileManager", "File write failed: " + e.toString());
        }
    }

    //deletes a file from internal storage at a path
    public static void deleteFile(String filepath,Context context)
    {
        try{
            File tempFile = new File(context.getFilesDir(),filepath);
            tempFile.delete();
        }catch (Exception e)
        {
            Log.e("FileManager","Delete failed" + e.toString());
        }
    }

    public static Date covertToDate(String pdate){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date timeStamp=new Date();
        try {
            timeStamp = df.parse(pdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }
}
