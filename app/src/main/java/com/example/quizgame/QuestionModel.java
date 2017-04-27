package com.example.quizgame;

import android.graphics.Bitmap;

/**
 * Model class for the question and answers
 * Written by Pooja.N.S 04/11/17
 */

public class QuestionModel {



    private String ID;
    private String question;
    private String answer;
    private String imagePath;
    private int score;
    private Bitmap clueImage;

    public QuestionModel()
    {

    }

    public QuestionModel(String pquestion, String panswer, String pimagePath,int pscore)
    {
        question = pquestion;
        answer = panswer;
        imagePath = pimagePath;
//        score = pscore;
        score = 3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public  String toString()
    {
        String data = this.getID() + "\t" +
                this.getQuestion() + "\t" +
                this.getAnswer() + "\t" +
                this.getImagePath() + "\t" +
                this.getScore() + "\n";
        return data;
    }

    public Bitmap getClueImage() {
        return clueImage;
    }

    public void setClueImage(Bitmap clueImage) {
        this.clueImage = clueImage;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
