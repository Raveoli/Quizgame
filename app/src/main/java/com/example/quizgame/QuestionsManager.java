/**
 * This file handles the manipulation, modification and save related to a QuestionModel object
 * Written by Pooja.N.S 04/11/17
 */

package com.example.quizgame;

import android.content.Context;

import java.util.ArrayList;

public class QuestionsManager {

    private static ArrayList<QuestionModel> questionsList;

    //adds the model object to the file
    public static void addQuestionToFile(QuestionModel questionModel,Context context)
    {
        //write image to internal storage
        String imagename = "Question_" + getNextQuestionID();
        String filename = imagename + ".jpg";
        FileManager.writeBitmapToFile(questionModel.getClueImage(),QuizGameConstants.imageDirectoryPath,filename,context);

        //set other fields
        questionModel.setID(imagename);
        questionModel.setImagePath(filename);
        questionModel.setScore(3);

        String data = questionModel.toString();

        //write model object to file
        FileManager.writeToFile(data,context,QuizGameConstants.fileDirectoryPath,QuizGameConstants.questionsFileName);
    }

    //for naming the corresponding image according to the question
    private static String getNextQuestionID()
    {
        Integer length = questionsList.size();
        return length.toString();
    }

    //reads the file for all the questions
    //useful for loading at initial startup
    public static void readAllQuestionsFromFile(Context context)
    {
       questionsList = FileManager.readFromQuestionsFile(QuizGameConstants.fileDirectoryPath,QuizGameConstants.questionsFileName,context);
    }

    //returns all the questions in the file
    public static ArrayList<QuestionModel> getQuestionsList() {
        return questionsList;
    }

}
