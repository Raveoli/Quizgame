package com.example.quizgame;

/**
 * Created by raveenahegde on 11/04/17.
 */

public class QuestionModel {
    private String question;
    private String answer;
    private String imagePath;
    private int score;

    public QuestionModel(String pquestion, String panswer, String pimagePath,int pscore)
    {
        question = pquestion;
        answer = panswer;
        imagePath = pimagePath;
        score = pscore;
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
        String data = this.getQuestion() + "\t" +
                this.getAnswer() + "\t" +
                this.getImagePath() + "\t" +
                this.getScore() + "\n";
        return data;
    }
}
