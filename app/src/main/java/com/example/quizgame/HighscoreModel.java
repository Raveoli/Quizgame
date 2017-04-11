package com.example.quizgame;

import java.util.Date;

/**
 * Created by raveenahegde on 11/04/17.
 */

public class HighscoreModel {
    private String name;
    private int highScore;
    private Date timeStamp;

    HighscoreModel(String pname,int phighScore,Date ptimeStamp){
        name=pname;
        highScore=phighScore;
        timeStamp=ptimeStamp;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public  String toString()
    {
        String data = this.getName() + "\t" +
                this.getHighScore() + "\t" +
                this.getTimeStamp() + "\n";
        return data;
    }
}
