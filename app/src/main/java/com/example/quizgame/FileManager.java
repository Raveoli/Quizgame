/**
 * This file contains all the file functionality to communicate with the internal storage
 * Written by Pooja.N.S 04/11/17
 */
package com.example.quizgame;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import static java.lang.Integer.parseInt;




//Class that manages file operations
public class FileManager {

    public static ArrayList<QuestionModel> readFromQuestionsFile(String directoryName, String fileName, Context context)
    {


        ArrayList<QuestionModel> questionsList = new ArrayList<QuestionModel>();
        try {
            File mydir = context.getDir(directoryName, Context.MODE_PRIVATE); //Creating an internal dir;
            File fileWithinMyDir = new File(mydir, fileName);
            InputStream inputStream = new FileInputStream(fileWithinMyDir); //context.openFileInput(filePath);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] splitString =  receiveString.split("\t");
                    QuestionModel c = new QuestionModel();//splitString[0],splitString[1],splitString[2],parseInt(splitString[3]));
                    c.setID(splitString[0]);
                    c.setQuestion(splitString[1]);
                    c.setAnswer(splitString[2]);
                    c.setImagePath(splitString[3]);
                    c.setScore(parseInt(splitString[4]));
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

    public static ArrayList<HighscoreModel> readFromHighscoreFile(String directoryName, String fileName, Context context)
    {
        ArrayList<HighscoreModel> highscoreList = new ArrayList<HighscoreModel>();
        try {
            File mydir = context.getDir(directoryName, Context.MODE_PRIVATE); //Creating an internal dir;
            File fileWithinMyDir = new File(mydir, fileName);
            InputStream inputStream = new FileInputStream(fileWithinMyDir);

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
    public static void writeToFile(String data,Context context, String directoryName, String fileName)
    {
        Log.d("Filemanager", "writeToFile: " + data + "at filepath " + fileName);
        try {
            File mydir = context.getDir(directoryName, Context.MODE_PRIVATE); //Creating an internal dir;
            File fileWithinMyDir = new File(mydir, fileName);
            FileOutputStream  outputStream = new FileOutputStream(fileWithinMyDir,true);//context.openFileOutput(filepath, Context.MODE_APPEND)
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("FileManager", "File write failed: " + e.toString());
        }
    }

    //deletes a file from internal storage at a path
    public static void deleteFile(String directoryName, String fileName,Context context)
    {
        try{
            File mydir = context.getDir(directoryName, Context.MODE_PRIVATE); //Creating an internal dir;
            File fileWithinMyDir = new File(mydir, fileName);
            fileWithinMyDir.delete();
        }catch (Exception e)
        {
            Log.e("FileManager","Delete failed" + e.toString());
        }
    }

    public static void writeBitmapToFile(Bitmap bitmap,String directoryName,String filename, Context context)
    {
        Log.d("FileManager","Writing to " + filename);
        File mydir = context.getDir(directoryName, Context.MODE_PRIVATE); //Creating an internal dir;
        File fileWithinMyDir = new File(mydir, filename);

      //  File file = new File(context.getFilesDir(), filename);
        if (fileWithinMyDir.exists())
            fileWithinMyDir.delete();
        try {
            FileOutputStream out = new FileOutputStream(fileWithinMyDir);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Date covertToDate(String pdate){
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US);
        Date timeStamp = new Date();
        try {
            timeStamp = df.parse(pdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }
}
