package com.example.quizgame;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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

        Comparator comparator =  new Comparator<HighscoreModel>() {
            @Override
            public int compare(final HighscoreModel object1, final HighscoreModel object2) {
                return object1.getTimeStamp().compareTo(object2.getTimeStamp());
            }
        };

        Comparator comparator1 =  Collections.reverseOrder(comparator);
        //sort by timestamp descending order
        Collections.sort(scoreList,comparator1);
        //sort by score ascending order
        Collections.sort(scoreList, new Comparator<HighscoreModel>() {
            @Override
            public int compare(final HighscoreModel object1, final HighscoreModel object2) {
                return object2.getHighScore().compareTo(object1.getHighScore());
            }
        } );

        //delete the existing file
        FileManager.deleteFile(QuizGameConstants.fileDirectoryPath, QuizGameConstants.highscoresFileName, context);

        //write all the scores to file
        int end = scoreList.size() <= 5 ? scoreList.size() :  5 ;
        for (int i = 0;i < end;i++) {
            HighscoreModel h = scoreList.get(i);
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

    public static boolean checkIfHighScore(int score,Context context)
    {
        readAllHighscoresFromFile(context);
        ArrayList<HighscoreModel>  hlist = getScoresList();
        if(hlist.size() == 0)
            return  true;

        int flag = 0;
        for(HighscoreModel model : hlist)
        {
            //check if the current score is higher than the existing score
            if(score >= model.getHighScore())
            {
                flag = 1;
            }
        }
        //if current score is less than the existing scores but the highscores list still has space
        if(flag == 0  && hlist.size() < 5)
            return true;
        else if(flag == 1) //current score is greater than existing scores
            return true;
        else
            return false;
    }
}
