package com.example.quizgame;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunny on 11-04-2017.
 */

public  class HighscoresManager {

    private static ArrayList<HighscoreModel> scoreList;

    //adds the model object to the file
    public static void addHighscoreToFile(HighscoreModel scoreModel,Context context)
    {
        scoreList.add(scoreModel);

        //sort by timestamp
        Collections.sort(scoreList, new Comparator<HighscoreModel>() {
            @Override
            public int compare(final HighscoreModel object1, final HighscoreModel object2) {
                return object1.getTimeStamp().compareTo(object2.getTimeStamp());
            }
        });

        //write all the scores to file
        for (HighscoreModel h : scoreList) {

            String data = h.toString();
            //write model object to file
            FileManager.writeToFile(data, context, QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName);
        }
    }

    public static void addSampleHighscores(Context context)
    {


        HighscoreModel h1 = new HighscoreModel("aaa",30,new Date());
        HighscoreModel h2 = new HighscoreModel("nnn",78,new GregorianCalendar(1992, Calendar.JANUARY,26).getTime());
        HighscoreModel h3 = new HighscoreModel("ggg",28,new Date());
        HighscoreModel h4 = new HighscoreModel("333",77,new Date());
        HighscoreModel h5 = new HighscoreModel("fdfdfdfdfsefwerwe",50,new Date());

        scoreList = new ArrayList<>();
        FileManager.deleteFile(QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName, context);
        addHighscoreToFile(h1,context);
        FileManager.deleteFile(QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName, context);
        addHighscoreToFile(h2,context);
        FileManager.deleteFile(QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName, context);
        addHighscoreToFile(h3,context);
        FileManager.deleteFile(QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName, context);
        addHighscoreToFile(h4,context);
        FileManager.deleteFile(QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName, context);
        addHighscoreToFile(h5,context);

    }

    //reads the file for all the highscores
    //useful for loading at initial startup
    public static void readAllHighscoresFromFile(Context context)
    {
        scoreList = FileManager.readFromHighscoreFile(QuizGameConstants.fileDirectoryPath,QuizGameConstants.highscoresFileName,context);
    }

    //returns all the highscores in the file
    public static ArrayList<HighscoreModel> getScoresList() {
        return scoreList;
    }
}
