package com.example.quizgame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public Integer getHighScore() {
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
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(this.getTimeStamp());
        String data = this.getName() + "\t" +
                this.getHighScore().toString() + "\t" +
                timestamp + "\n";
        return data;
    }
}
